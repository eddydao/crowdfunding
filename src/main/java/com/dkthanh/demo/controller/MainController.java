package com.dkthanh.demo.controller;

import com.dkthanh.demo.dao.MaterialTypeRepository;
import com.dkthanh.demo.domain.*;
import com.dkthanh.demo.domain.dto.OptionDto;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import com.dkthanh.demo.domain.dto.StoryDto;
import com.dkthanh.demo.domain.dto.UploadFormDto;
import com.dkthanh.demo.dto.ProjectDto;
import com.dkthanh.demo.service.*;
import com.dkthanh.demo.util.Constant;
import com.dkthanh.demo.util.WebUtil;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialTypeRepository materialTypeRepository;

    @Autowired
    private StoryService storyService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ResourcePath resourcePath;

    @Autowired
    private PackageService packageService;

    @Autowired
    private PaypalService paypalService;

    public static final String RELATIVE_PATH = "../../../";
    public static final String REPLACE_THUMBNAIL_PATH = "/creator/images/bg-title-01.jpg";

    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    @Value("${stripe.publickey}")
    private String publicKey;

    private Logger log = LoggerFactory.getLogger(getClass());

    /*
     *  Common function
     * ===========================================
     */
    private String doUpload(UploadFormDto myUploadForm) {

        String description = myUploadForm.getImageName();
        System.out.println("Description: " + description);

        // Thư mục gốc upload file.
        String path = System.getProperty("user.dir");
        String uploadRelativePath = "images/project-assets/" + myUploadForm.getProjectId();
        String uploadRootPath = path + "/src/main/resources/static/" + uploadRelativePath;
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile fileDatas = myUploadForm.getFileDatas();
        // Tên file gốc tại Client.
        String name = fileDatas.getOriginalFilename();
        System.out.println("Client File Name = " + name);

        if (name != null && name.length() > 0) {
            try {
                // Tạo file tại Server.
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileDatas.getBytes());
                stream.close();
                System.out.println("Write file: " + serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + name);
            }
        }
        System.out.println("File location = " + uploadRelativePath +  File.separator + name);
        return uploadRelativePath +  File.separator + name;
    }

    // load thumbnail image
    @RequestMapping(value = { "/project/image/{project_id}" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @PathVariable("project_id") int projectId) throws IOException {
        String thumbnailImagePath = projectService.getProjectEntityById(projectId).getThumbnailPath() != null ? projectService.getProjectEntityById(projectId).getThumbnailPath() : REPLACE_THUMBNAIL_PATH;
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        String s = resourcePath.getPath();
        String absolutePath = s + thumbnailImagePath;
        File f = new File(absolutePath);
        if(f.exists() && !f.isDirectory()){
            response.getOutputStream().write(WebUtil.extractByte(absolutePath));
        }
        response.getOutputStream().close();
    }

    //    Open register page
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String openRegisterForm(Model model){
        NewUserDTO newUserDTO = new NewUserDTO();
        model.addAttribute("newUserForm", newUserDTO);
        return "register-page";
    }

    //    Save new user
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewAccount(Model model, @ModelAttribute("newUserForm") @Validated NewUserDTO newUserDTO, BindingResult result, final RedirectAttributes redirectAttributes){
        // Validate result
        if (result.hasErrors()) {
            return "404";
        }

        UserEntity newUser = new UserEntity();
        try{
            newUser = userService.saveUser(newUserDTO);
        }catch (Exception e){
            return "register";
        }
        return "temp-result";
    }

    // open login form
    @GetMapping(value = "/signin")
    public String login() {
        return "/login-page";
    }

    // search function - not complete
    @PostMapping(value = "/search", produces = { "application/json" })
    public String search(@RequestParam("search_box") String keyword, Model model){
        if (StringUtils.isEmpty(keyword)) {
            model.addAttribute("result", 0);
            model.addAttribute("infor", new String("We can't find any result with your keyword"));
            return "client/search_result";
        }
        model.addAttribute("result", 1);
        model.addAttribute("search_results", projectService.searchProjectByNameContaining(keyword));
        model.addAttribute("tags", categoryService.getAllCategory());
        return "/search_result";
    }

    public String modifiedResourceRelativePath(String content, boolean isMerge){
        StringBuilder modifiedContent = null;
        if(!isMerge){
            String[] contentArray = content.split(RELATIVE_PATH);
            modifiedContent = new StringBuilder();
            for (int i = 0; i < contentArray.length; i++){
                modifiedContent.append(contentArray[i]);
            }
        }else{
            String[] contentArray = content.split("<img src=\"");
            modifiedContent = new StringBuilder();
            for (int i = 0; i < contentArray.length-1; i++){
                modifiedContent.append(contentArray[i] + "<img src=\"../../../");
            }
            modifiedContent.append(contentArray[contentArray.length-1]);
        }
        return modifiedContent.toString();
    }


    /*
     *  Index page function
     * ===========================================
     */
    @GetMapping(value = "/index")
    public String index(Model model){
        List<CategoryEntity> categoryEntityList = categoryService.getAllCategory();
        List<ProjectFullInfoEntity> popularProjects = projectService.getPopularProjects();
        ProjectFullInfoEntity recommendedProject = projectService.getRecommendedProject();
        for(int i = 0; i < popularProjects.size(); i++){
            popularProjects.get(i).setThumbnailPath(RELATIVE_PATH+ popularProjects.get(i).getThumbnailPath());
        }
        
        recommendedProject.setThumbnailPath(RELATIVE_PATH+ recommendedProject.getThumbnailPath());
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("popular_projects", popularProjects);
        model.addAttribute("recommended_project", recommendedProject);
        return "index";
    }

    /*
    *   Index page
     */
    @GetMapping(value = "/")
    public String getHomePage(){
        return "redirect:/index";
    }

    /*
     *  Project detail page function
     * ===========================================
     */

    //  load project detail page
    @GetMapping(value = "/project/{id}")
    public String getProjectDetailPage(Model model, @PathVariable("id") Integer id){
        ProjectFullInfoEntity p = projectService.getProjectDetail(id);
        StoryEntity story = storyService.getStoryByProjectId(id);
        List<OptionEntity> options =  optionService.getOptionListByProjectId(id);
        p.setThumbnailPath(RELATIVE_PATH+ p.getThumbnailPath());
        List<OptionDto> optionDtos = new ArrayList<>();
        for(int i = 0; i < options.size(); i++){
            OptionEntity option = options.get(i);
            OptionDto optionDto = new OptionDto(option.getOptionId(),
                                                option.getOptionName(),
                                                option.getOptionDescription(),
                                                option.getFundMin(),
                                                option.getItems(),
                                                option.getProject().getProjectId(),
                                         null);
            optionDtos.add(optionDto);
        }

        model.addAttribute("project", p);
        model.addAttribute("story", story);
        model.addAttribute("options", optionDtos);
        return "project-detail";
    }

    /*
    Fund the project
     */

//    @PostMapping(value = "/project/fund-project")
//    public String fundTheProject(HttpServletRequest request,Model model, @ModelAttribute("option") @Validated OptionDto dto,
//                                 BindingResult result, final RedirectAttributes redirectAttributes){
//        Package userChoosedPack =  new com.dkthanh.demo.domain.Package();
//        Integer optionId = dto.getOptionId();
//        Integer projectId = dto.getProjectId();
//        Long pledge = dto.getPledge();
//        Integer userId  = 2;
//        UserEntity user = userService.findUserById(userId);
//
//
//        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
//        OptionEntity optionEntity = optionService.getOptionByProjectIdAndOptionId(projectId, optionId);
//
//        userChoosedPack.setProject(projectEntity);
//        userChoosedPack.setOption(optionEntity);
//        userChoosedPack.setPledged(pledge);
//        userChoosedPack.setUser(user);
//        packageService.savePackage(userChoosedPack);
//
//        // return to check out screen -> apply paypal here first, then save the package infors
//        return null;
//    }

    @PostMapping(value = "/project/fund-project")
    public String paymentWithPaypal(HttpServletRequest request,Model model, @ModelAttribute("option") @Validated OptionDto dto,
                                               BindingResult result, final RedirectAttributes redirectAttributes){
        String cancelUrl = WebUtil.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = WebUtil.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        try {
            Payment payment = paypalService.createPayment(
                    dto.getPledge(),
                    "USD",
                    Constant.PaymentMethod.PAYPAL,
                    Constant.PaypalPaymentIntent.SALE,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
//    public String paymentWithPaypal(HttpServletRequest request, Integer price){
//        String cancelUrl = WebUtil.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
//        String successUrl = WebUtil.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
//        try {
//            Payment payment = paypalService.createPayment(
//                    price,
//                    "USD",
//                    Constant.PaymentMethod.PAYPAL,
//                    Constant.PaypalPaymentIntent.SALE,
//                    "payment description",
//                    cancelUrl,
//                    successUrl);
//            for(Links links : payment.getLinks()){
//                if(links.getRel().equals("approval_url")){
//                    return "redirect:" + links.getHref();
//                }
//            }
//        } catch (PayPalRESTException e) {
//            log.error(e.getMessage());
//        }
//        return "redirect:/";
//    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "403";
    }


    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "payment-success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    /*
     *  Creator management function
     * ===========================================
     * creator/project/list
     * creator/create-project
     * creator/project/id
     * creator/project/save
     * creator/project/edit/id
     * creator/project/delete/id
     */

    @GetMapping(value = "/creator")
    public String creatorPage(){
        return "redirect:/creator/project/list";
    }

    // creator project/list
    @GetMapping(value = "/creator/project/list" )
    public String getCreatorProjectList(Model model, Authentication authentication){
        String username = null;
        UserDetailEntity userDetailEntity = null;
        UserEntity user = null;
        if(authentication != null) {

            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        if(!"".equals(username)){
            user = userService.findUserByUsername(username);
        }

//        Integer userId  = user.getId();
        Integer userId  = 3;
        userDetailEntity = userDetailService.getUserDetailByUserId(userId);

        List<ProjectFullInfoEntity> list = projectService.getProjectListOfCreator(userId);
        model.addAttribute("projects", list);
        model.addAttribute("creator", userDetailEntity);
        return "/creator/creator-dashboard";
    }

    /*
    *   run this when user click to create project
     */
    @GetMapping(value = "/creator/create-project")
    public String openCreateProjectForm(Model model){
        ProjectEntity projectEntity = projectService.saveProjectEntity(new ProjectEntity());
        int projectId = projectEntity.getProjectId();
        ProjectDto dto = new ProjectDto();
        dto.setProjectId(projectId);
        model.addAttribute("allCategory", categoryService.getAllCategory());
        model.addAttribute("project_dto", dto);
        return "redirect:/creator/project/" + projectId + "/basic";
    }

    /*
    *   redirect all request of edit or create a new project to this
    *   send information of project modification to UI from here
     */
    @GetMapping(value = "/creator/project/{projectId}")
    public String openProjectEditForm(Model model, @PathVariable("projectId") Integer projectId){
        ProjectDto dto = new ProjectDto();
        ProjectEntity entity = projectService.getProjectEntityById(projectId);
        if(entity.getProjectName() != null){
            dto.setProjectName(entity.getProjectName());
        }
        dto.setProjectId(projectId);
        model.addAttribute("allCategory", categoryService.getAllCategory());
        model.addAttribute("project_dto", dto);
        return "/creator/overview";
    }

    /*
    *   return basic information of project
     */

    @GetMapping(value = "/creator/project/{projectId}/basic")
    public String getProjectBasicInfoForm(Model model, @PathVariable("projectId") Integer projectId){
        ProjectDto dto = new ProjectDto();

        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        dto.setProjectId(projectId);
        dto.setStep(Constant.ProjectFormStep.BASIC.getId());
        if(projectEntity != null){
            dto.setProjectName(projectEntity.getProjectName());
            dto.setSubTitle(projectEntity.getProjectShortDes());
            dto.setCategoryId(projectEntity.getCategory()!= null ? projectEntity.getCategory().getId() : null);
            dto.setThumbnailPathFile(projectEntity.getThumbnailPath() != null ? RELATIVE_PATH + projectEntity.getThumbnailPath() : REPLACE_THUMBNAIL_PATH);
            dto.setGoal(projectEntity.getGoal());
        }

        model.addAttribute("allCategory", categoryService.getAllCategory());
        model.addAttribute("project_dto", dto);
        return "/creator/project-basic";
    }

    /*
     *   save project with basic information from form
     *   not validate infor yet
     */
    @PostMapping(value = "/creator/save-project-basic")
    public String saveProjectBasic(HttpServletRequest request,Model model, @ModelAttribute("project_dto") @Validated ProjectDto dto,
                              BindingResult result, final RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "redirect:/index";
        }
        // ProjectEntity projectEntity = new ProjectEntity();
        ProjectEntity projectEntity = projectService.getProjectEntityById(dto.getProjectId());
        int step = dto.getStep();
        // insert basic information
        if(step == 1){
            // get category detail
            CategoryEntity category = categoryService.getCategoryById(dto.getCategoryId());
            UploadFormDto uploadDto = new UploadFormDto(dto.getProjectId(), dto.getImageName(), dto.getFileDatas());
            projectEntity.setProjectId(dto.getProjectId());
            projectEntity.setProjectName(dto.getProjectName());
            projectEntity.setProjectShortDes(dto.getSubTitle());
            projectEntity.setCategory(category);
            projectEntity.setGoal(dto.getGoal());
            projectEntity.setThumbnailPath(this.doUpload(uploadDto));
        }

        projectService.saveProjectEntity(projectEntity);
        return "redirect:/creator/project/"+ dto.getProjectId();
    }

    /*
    * Return reward information of project
     */

    @GetMapping(value = "/creator/project/{projectId}/reward")
    public String getProjectFundingInfoForm(Model model, @PathVariable("projectId") Integer projectId){
        ProjectDto dto = new ProjectDto();
        ProjectFullInfoEntity fullInfoEntity = projectService.getProjectDetail(projectId);

        dto.setProjectId(projectId);
        dto.setStep(Constant.ProjectFormStep.REWARD.getId());

        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        List<OptionEntity> optionList = null;
        List<ItemEntity> itemList = null;

        if(projectEntity != null){
            optionList = optionService.getOptionListByProjectId(projectId);
            itemList= itemService.getItemsOfProject(projectId);
        }

        model.addAttribute("allCategory", categoryService.getAllCategory());
        model.addAttribute("options", optionList);
        model.addAttribute("items", itemList);
        model.addAttribute("project_dto", dto);
        return "/creator/project-reward";
    }

    /*
    Open create new reward modal
     */

    @GetMapping(value = "/creator/project/{projectId}/create-reward-form")
    public String createProjectReward(Model model,  @PathVariable("projectId") Integer projectId) {
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        OptionDto dto = new OptionDto(null, null,
                null, null, null, projectId, null);
        model.addAttribute("projectId", projectId);
        model.addAttribute("option", dto);
        model.addAttribute("items", null);
        return "/creator/fragments/modal :: createReward";
    }
    /*
    Open edit reward modal
     */

    @GetMapping(value = "/creator/project/{projectId}/reward/{optionId}")
    public String getProjectItemByProjectId(Model model,  @PathVariable("projectId") Integer projectId,
            @PathVariable("optionId") Integer optionId){
//        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        OptionEntity optionEntity = optionService.getOptionByProjectIdAndOptionId(projectId, optionId);
        OptionDto dto = new OptionDto(optionEntity.getOptionId(), optionEntity.getOptionName(), optionEntity.getOptionDescription(), optionEntity.getFundMin(), optionEntity.getItems(), projectId, null);
        model.addAttribute("projectId", projectId);
        model.addAttribute("option", dto);
        model.addAttribute("items", optionEntity.getItems());
        return "/creator/fragments/modal :: editRewardModal";
    }

    /*
    Open select item modal
     */

    @PostMapping(value = "/creator/project/reward/addItemModal")
    public String openItemSelectionModal(HttpServletRequest request,Model model, @ModelAttribute("option") @Validated OptionDto dto,
                                         BindingResult result, final RedirectAttributes redirectAttributes){
        Integer optionId = dto.getOptionId();
        Integer projectId = dto.getProjectId();
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        OptionEntity optionEntity ;
        if(optionId != null){
            optionEntity = optionService.getOptionByProjectIdAndOptionId(projectId, optionId);
            dto = new OptionDto(optionEntity.getOptionId(), optionEntity.getOptionName(), optionEntity.getOptionDescription(), optionEntity.getFundMin(), optionEntity.getItems(), projectId, null);
        }
        model.addAttribute("projectId", projectId);
        model.addAttribute("option", dto);
        model.addAttribute("items", projectEntity.getItems());
        return "/creator/fragments/modal :: addItemModal";
    }




    /*
    Create new reward
     */
    @PostMapping(value = "/creator/save-reward", params = "action=save")
    public String createProjectReward(HttpServletRequest request,Model model, @ModelAttribute("option") @Validated OptionDto optionDto,
                                      BindingResult result, final RedirectAttributes redirectAttributes){
        Integer projectId  = optionDto.getProjectId();
        OptionEntity optionEntity = new OptionEntity();
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        if(optionDto.getOptionId() == null ){
            optionEntity.setOptionName(optionDto.getOptionName());
            optionEntity.setOptionDescription(optionDto.getOptionDescription());
            optionEntity.setFundMin(optionDto.getFundMin());
            optionEntity.setItems(optionDto.getItems());
            optionEntity.setProject(projectEntity);
        } else {
            optionEntity.setOptionId(optionDto.getOptionId());
            optionEntity.setOptionName(optionDto.getOptionName());
            optionEntity.setOptionDescription(optionDto.getOptionDescription());
            optionEntity.setFundMin(optionDto.getFundMin());
            optionEntity.setItems(optionDto.getItems());
            optionEntity.setProject(projectEntity);
        }
        projectEntity.getOptions().add(optionEntity);
        projectService.saveProjectEntity(projectEntity);
        return "redirect:/creator/project/" +projectId + "/reward" ;
    }
    /*
    *   Open story edit page
     */

    @GetMapping(value = "/creator/project/{projectId}/story")
    public String getProjectStoryForm(Model model, @PathVariable("projectId") Integer projectId){
        ProjectDto dto = new ProjectDto();
//        ProjectFullInfoEntity fullInfoEntity = projectService.getProjectDetail(projectId);

        dto.setProjectId(projectId);
        dto.setStep(Constant.ProjectFormStep.STORY.getId());

        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        StoryEntity story = null;

        if(projectEntity != null){
            story = storyService.getStoryByProjectId(projectId);
        }

        if(story == null){
            story = new StoryEntity();
            story.setProjectId(projectId);
            story.setProject(projectEntity);
            storyService.save(story);
        }
        StoryDto storyDto = new StoryDto(projectId, modifiedResourceRelativePath(story.getStoryDes(), true) );
        model.addAttribute("allCategory", categoryService.getAllCategory());
        model.addAttribute("project_dto", dto);
        model.addAttribute("story_dto", storyDto);
        return "/creator/project-story";
    }

    @PostMapping(value = "/creator/project/story/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam(value = "image") MultipartFile uploadfiles, @RequestParam(value = "projectId") Integer projectId){
        UploadFormDto uploadFormDto = new UploadFormDto(projectId, uploadfiles);
        String pathFile  = RELATIVE_PATH + this.doUpload( uploadFormDto);  // get relative path for returned image path uploaded by creator;
        return new ResponseEntity<String>(pathFile,HttpStatus.OK);
    }

    @PostMapping(value = "creator/save-project-story")
    public String saveProjectStory(HttpServletRequest request,Model model, @ModelAttribute("story") @Validated StoryEntity story,
                                    BindingResult result, final RedirectAttributes redirectAttributes){
        String content = story.getStoryDes();

        modifiedResourceRelativePath(content, false);
        Integer projectId = story.getProjectId();
        StoryEntity storyEntity = storyService.getStoryByProjectId(projectId);
        storyEntity.setStoryDes(content);
        storyService.save(storyEntity);
        return "redirect:/creator/project/"+ projectId;
    }


    @GetMapping(value = "/creator/report")
    public String getReport(Model model){
        return "/creator/creator-report";
    }

    /*
    *  Admin management function
    * ===========================================
    admin/project/list
    admin/project/id
    admin/category/list
    admin/project/approval
    admin/user/list
    admin/
     */


    // CATEGORY MANAGEMENT
    //==================================================================================================================
    // category list for admin view
    @GetMapping(value = "/admin/category/list")
    public String getCategoryList(Model model){
        List<CategoryEntity>  categoryEntities = categoryService.getAllCategory();
        model.addAttribute("categories", categoryEntities);
        return "/admin/category-management";
    }

    /*
    Create new category
     */
    @PostMapping(value = "/admin/category/save")
    public String createNewCategory(HttpServletRequest request,Model model, @ModelAttribute("category") @Validated CategoryEntity inputCategoryEntity,
                                    BindingResult result, final RedirectAttributes redirectAttributes){
        Integer categoryId = inputCategoryEntity.getId();
        CategoryEntity entity = new CategoryEntity();
        if(categoryId != null){
            entity = categoryService.getCategoryById(categoryId);
        }
        entity.setName(inputCategoryEntity.getName());
        categoryService.save(entity);
        List<CategoryEntity>  categoryEntities = categoryService.getAllCategory();
        model.addAttribute("categories", categoryEntities);
        return "admin/category-management :: category-table";
    }

    /*
    Delete confirmation modal
     */
    @GetMapping(value = "/admin/category/del-confirmation/{categoryId}")
    public String openDeleteConfirmationModal(Model model,  @PathVariable("categoryId") Integer categoryId){
        CategoryEntity categoryEntity = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", categoryEntity);
        return "/admin/fragments/modal :: categoryDelConfirmation";
    }

    /*
    Delete category
     */
    @PostMapping(value = "/admin/category/del")
    public String delCategory(HttpServletRequest request,Model model, @ModelAttribute("category") @Validated CategoryEntity categoryEntity,
                                   BindingResult result, final RedirectAttributes redirectAttributes){
        boolean isDeleted = categoryService.deleteCategory(categoryEntity.getId());
        if(isDeleted){
            model.addAttribute("messageSuccess", "Delete success");
        }else{
            model.addAttribute("messageError", "Delete fail");
        }
        List<CategoryEntity>  categoryEntities = categoryService.getAllCategory();

        model.addAttribute("categories", categoryEntities);
        return "admin/category-management :: category-table";
    }

    //END CATEGORY MANAGEMENT
    //===============================================================================================

    // get admin dashboard
    @GetMapping(value = "/admin/dashboard")
    public String getAdminDashboard(){
        return null;
    }

    // get project list
    @GetMapping(value = "/admin/project/list")
    public String getAdminProjectList(){
        return null;
    }

    // get pending list of project that need approval
    @GetMapping(value = "/admin/project/pending-list")
    public String getAdminPendingList(){
        return null;
    }
}
