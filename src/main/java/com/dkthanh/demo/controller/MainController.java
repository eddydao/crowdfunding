package com.dkthanh.demo.controller;

import com.dkthanh.demo.dao.MaterialTypeRepository;
import com.dkthanh.demo.domain.Package;
import com.dkthanh.demo.domain.*;
import com.dkthanh.demo.domain.dto.*;
import com.dkthanh.demo.dto.ProjectDto;
import com.dkthanh.demo.service.*;
import com.dkthanh.demo.util.Constant;
import com.dkthanh.demo.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.*;

@CrossOrigin("*")
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

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

//    @Autowired
//    private PaypalService paypalService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private OptionItemService optionItemService;

    public static final String RELATIVE_PATH = "../../../";
    public static final String REPLACE_THUMBNAIL_PATH = "/creator/images/bg-title-01.jpg";

    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    public static final Integer CENT_TRANSFER = 100;

    @Value("${stripe.publickey}")
    private String publicKey;

//    @Value("${stripe.secretkey}")
//    private String secretKey;

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

    // search function
    @PostMapping(value = "/search", produces = { "application/json" })
    public String search(@RequestParam("search_box") String keyword, Model model){
        if (StringUtils.isEmpty(keyword)) {
            model.addAttribute("result", 0);
            model.addAttribute("infor", new String("We can't find any result with your keyword"));
            return "list-project";
        }
        model.addAttribute("result", 1);
        model.addAttribute("projects", projectService.searchProjectByNameContaining(keyword));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "list-project";
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
        if(popularProjects != null && !popularProjects.isEmpty()){
            for(int i = 0; i < popularProjects.size(); i++){
                popularProjects.get(i).setThumbnailPath(RELATIVE_PATH+ popularProjects.get(i).getThumbnailPath());
            }
        }
        if(recommendedProject != null ){
            recommendedProject.setThumbnailPath(RELATIVE_PATH+ recommendedProject.getThumbnailPath());
        }

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

            List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(id, option.getOptionId());
            OptionDto optionDto = new OptionDto(option.getOptionId(),
                                                option.getOptionName(),
                                                option.getOptionDescription(),
                                                option.getFundMin(),
                                                listItem,
                                                option.getProject().getProjectId(),
                                         null);
            optionDtos.add(optionDto);
        }

        model.addAttribute("project", p);
        model.addAttribute("story", story);
        model.addAttribute("options", optionDtos);
        return "project-detail";
    }

    @GetMapping(value = "/category/{id}")
    public String getListProjectById(Model model, @PathVariable("id") Integer id){
        List<ProjectFullInfoEntity> list = projectService.getProjectListByCategoryId(id);
        for (ProjectFullInfoEntity p: list) {
            p.setThumbnailPath(RELATIVE_PATH+ p.getThumbnailPath());
        }
        model.addAttribute("projects", list);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "list-project";
    }


    @GetMapping(value = "/project/all")
    public String getAllProject(Model model){
        List<ProjectFullInfoEntity> list = projectService.getAllRunningProjectFullEntity();
        for (ProjectFullInfoEntity p: list) {
            p.setThumbnailPath(RELATIVE_PATH+ p.getThumbnailPath());
        }

        model.addAttribute("projects", list);
        return "list-project";
    }

    /*
    Fund the project
     */
    @PostMapping(value = "/fund-project")
    public String fundTheProject(HttpServletRequest request,Model model, @ModelAttribute("option") @Validated OptionDto dto,
                                               BindingResult result, final RedirectAttributes redirectAttributes) {

        model.addAttribute("stripePublicKey" , publicKey);
        Integer projectId = dto.getProjectId();
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        model.addAttribute("option", dto);
        model.addAttribute("project", projectEntity);
        return "checkout";
    }

    @PostMapping(value = "/create-charge")
    public @ResponseBody ChargeRequestEntity createCharge(String email, String token, String optionId, String projectId, Integer pledge){
        //validate data
        if (token == null) {
            return new ChargeRequestEntity(false, "Stripe payment token is missing. Please, try again later.");
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        UserEntity user = null;

        // in real run, uncomment this block
        if(authentication != null) {

            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        if(!"".equals(username)){
            user = userService.findUserByUsername(username);
        }

        Integer userId  = user.getId();

        // update info of project
        ProjectEntity projectEntity = projectService.getProjectEntityById(Integer.parseInt(projectId));
        projectEntity.setPledged(projectEntity.getPledged() != null ? projectEntity.getPledged() + pledge : pledge);

        int count = 0;
        List<Package> listExistPack = packageService.getAllPackageByProjectId(Integer.parseInt(projectId));
        for(int i = 0; i< listExistPack.size(); i++){
            if(username.equals(listExistPack.get(i).getUser().getUsername()) ){
                count++;
            }
        }

        if(count == 0){
            projectEntity.setInvestorCount(projectEntity.getInvestorCount() + 1);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PackageDto dto = new PackageDto(userId, Integer.parseInt(projectId), Integer.parseInt(optionId), new Long(pledge),timestamp);

        int rowCount = packageService.customSavePackage(dto);
        System.out.println("ROWCOUNT..........." + rowCount);

        String chargeId = stripeService.createCharge(email, token, pledge * CENT_TRANSFER);
        if (chargeId == null) {
            return new ChargeRequestEntity(false, "An error occurred while trying to create a charge.");
        }
        return new ChargeRequestEntity(true, "Success! Your charge id is " + chargeId);
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

        Integer userId  = user.getId();
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
    public String openCreateProjectForm(Model model, Authentication authentication){

        String username = null;
        UserEntity user = null;
        if(authentication != null) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        if(!"".equals(username)){
            user = userService.findUserByUsername(username);
        }

        ProjectEntity p = new ProjectEntity();
        StatusEntity st = statusService.getStatusById(Constant.ProjectStatus.EDITING.getId());
        p.setUser(user);
        p.setProjectStatus(st);
        ProjectEntity projectEntity = projectService.saveProjectEntity(p);
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
        String thumbnailPath = projectEntity.getThumbnailPath();
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
            projectEntity.setThumbnailPath(  (uploadDto.getFileDatas() != null && !uploadDto.getFileDatas().isEmpty()) ? this.doUpload(uploadDto) : thumbnailPath);
        }

        projectService.saveProjectEntity(projectEntity);
        return "redirect:/creator/project/"+ dto.getProjectId();
    }
    //==================================================================================================================
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
        List<OptionDto> optionDtos = new ArrayList<>();

        if(optionList != null && !optionList.isEmpty()){
            for(int i = 0; i < optionList.size(); i++){
                OptionEntity option = optionList.get(i);

                List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, option.getOptionId());
                OptionDto optionDto = new OptionDto(option.getOptionId(),
                        option.getOptionName(),
                        option.getOptionDescription(),
                        option.getFundMin(),
                        listItem,
                        option.getProject().getProjectId(),
                        null);
                optionDtos.add(optionDto);
            }
        }
        OptionDto optionDto = new OptionDto(null, null,
                null, null, null, projectId, null);

        model.addAttribute("allCategory", categoryService.getAllCategory());
        model.addAttribute("options", optionDtos);
        model.addAttribute("items", itemList);
        model.addAttribute("project_dto", dto);
        model.addAttribute("option", optionDto);
//        model.addAttribute("item", null);
        model.addAttribute("projectId", projectId);
        return "/creator/project-reward";
    }


    /*
    * Open create new option modal
     */

    @GetMapping(value = "/creator/project/{projectId}/reward/new-option")
    public String openCreateRewardModal(Model model,  @PathVariable("projectId") Integer projectId){
        OptionDto dto = new OptionDto();
        dto.setProjectId(projectId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("option", dto);
        model.addAttribute("items", null);
        return "/creator/fragments/modal :: createRewardArea";
    }


    /*
    Open edit reward modal
     */

    @GetMapping(value = "/creator/project/{projectId}/reward/{optionId}")
    public String openEditRewardModal(Model model,  @PathVariable("projectId") Integer projectId,
            @PathVariable("optionId") Integer optionId){
            OptionEntity optionEntity = optionService.getOptionByProjectIdAndOptionId(projectId, optionId);
            List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, optionId);
            OptionDto dto = new OptionDto(optionEntity.getOptionId(), optionEntity.getOptionName(), optionEntity.getOptionDescription(), optionEntity.getFundMin(), listItem, projectId, null);
            model.addAttribute("projectId", projectId);
            model.addAttribute("option", dto);
            model.addAttribute("items", optionEntity.getItems());
            return "/creator/fragments/modal :: editRewardArea";
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
            List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, optionId);
            dto = new OptionDto(optionEntity.getOptionId(), optionEntity.getOptionName(), optionEntity.getOptionDescription(), optionEntity.getFundMin(), listItem, projectId, null);
        }
        model.addAttribute("projectId", projectId);
        model.addAttribute("option", dto);
        model.addAttribute("items", projectEntity.getItems());
        return "/creator/fragments/modal :: addItemModal";
    }



    @GetMapping(value = "creator/project/reward/addItemToList")
    public String addItemToList( Model model, OptionDto dto){
        Integer projectId = dto.getProjectId();
        Integer itemId = dto.getNewItemId();
        Integer optionId = dto.getOptionId();
        optionItemService.saveNewOptionItem(optionId, itemId, null);
        OptionEntity optionEntity = optionService.getOptionByProjectIdAndOptionId(projectId, optionId);
        List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, optionId);
        OptionDto optionDto = new OptionDto(optionEntity.getOptionId(), optionEntity.getOptionName(), optionEntity.getOptionDescription(), optionEntity.getFundMin(), listItem, projectId, null);

//        ItemEntity item = itemService.findItemByItemId(itemId);


//        item.setOption(optionEntity);
//        optionEntity.getItems().add(item);
//        optionEntity = optionService.save(optionEntity);


        model.addAttribute("projectId", projectId);
        model.addAttribute("option", optionDto);
        model.addAttribute("items", optionEntity.getItems());
        return "/creator/fragments/modal :: editRewardArea";
    }

    @GetMapping(value = "/creator/project/confirm-remove-item/")
    public String confirmRemoveItemFromList(Model model, OptionDto dto){

        model.addAttribute("dto" , dto);
        return "/creator/fragments/modal :: removeItemModal";
    }

    @PostMapping(value = "/creator/project/reward/remove-item")
    public String removeItemFromList(Model model, OptionDto dto){
        Integer projectId = dto.getProjectId();
        Integer itemId = dto.getNewItemId();
        Integer optionId = dto.getOptionId();

        boolean isDeleted = optionItemService.removeOptionItemById(optionId, itemId);

        OptionEntity optionEntity = optionService.getOptionByProjectIdAndOptionId(projectId, optionId);
        List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, optionId);
        OptionDto optionDto = new OptionDto(optionEntity.getOptionId(), optionEntity.getOptionName(), optionEntity.getOptionDescription(), optionEntity.getFundMin(), listItem, projectId, null);


        model.addAttribute("projectId", projectId);
        model.addAttribute("option", optionDto);
        model.addAttribute("items", optionEntity.getItems());
        return "/creator/fragments/modal :: editRewardArea";
    }



    /*
    Create new reward
     */
    @PostMapping(value = "/creator/save-reward")
    public String createProjectReward(HttpServletRequest request,Model model, @ModelAttribute("option") @Validated OptionDto optionDto,
                                      BindingResult result, final RedirectAttributes redirectAttributes){
        Integer projectId  = optionDto.getProjectId();
        OptionEntity optionEntity = new OptionEntity();
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, optionDto.getOptionId());
        if(optionDto.getOptionId() == null ){
            optionEntity.setOptionName(optionDto.getOptionName());
            optionEntity.setOptionDescription(optionDto.getOptionDescription());
            optionEntity.setFundMin(optionDto.getFundMin());
//            optionEntity.setItems(optionDto.getItems());
            optionEntity.setProject(projectEntity);
        } else {
            optionEntity.setOptionId(optionDto.getOptionId());
            optionEntity.setOptionName(optionDto.getOptionName());
            optionEntity.setOptionDescription(optionDto.getOptionDescription());
            optionEntity.setFundMin(optionDto.getFundMin());
//            optionEntity.setItems(optionDto.getItems());
            optionEntity.setProject(projectEntity);
        }
        projectEntity.getOptions().add(optionEntity);
        projectService.saveProjectEntity(projectEntity);
        return "redirect:/creator/project/" +projectId + "/reward" ;
    }

    /*
    Open item creation modal
     */

    @GetMapping(value = "/creator/project/open-item-creation-modal")
    public String openItemCreationModal(Model model, ItemDto dto){
        Integer projectId = dto.getProjectId();
        model.addAttribute("projectId", projectId);

        return "/creator/fragments/modal :: addNewItem";
    }


    /*
    Open item edit modal
     */
    @GetMapping(value = "/creator/project/open-item-edit-modal")
    public String openItemEditModal(Model model, ItemDto dto){
        Integer projectId = dto.getProjectId();
        Integer itemId = dto.getItemId();
        ItemEntity item = itemService.findItemByItemId(itemId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("item", item);

        return "/creator/fragments/modal :: editItem";
    }

    /*
    Add new item to project
     */

    @PostMapping(value = "/creator/project/save-item")
    public String saveItem(Model model, ItemDto dto){

        Integer projectId = dto.getProjectId();
        String itemName = dto.getItemName();
        Integer itemId = dto.getItemId();
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        ItemEntity itemEntity = new ItemEntity(itemName, projectEntity);
        if(itemId != null){
            itemEntity.setItemId(itemId);
        }

        itemService.saveNewItem(itemEntity);

        ProjectFullInfoEntity fullInfoEntity = projectService.getProjectDetail(projectId);

        dto.setProjectId(projectId);

        List<ItemEntity> itemList = null;

        if(projectEntity != null){
            itemList= itemService.getItemsOfProject(projectId);
        }

        model.addAttribute("items", itemList);
        model.addAttribute("project_dto", fullInfoEntity);
        return "/creator/project-reward :: item-list-div";
    }

    /*
    call delete item modal
     */
    @PostMapping(value = "/creator/project/delete-item-confirmation")
    public String showItemDeleteConfirmationModal(Model model, ItemDto itemDto){
        Integer itemId = itemDto.getItemId();
        Integer projectId = itemDto.getProjectId();

        model.addAttribute("projectId", projectId);
        model.addAttribute("itemId", itemId);

        return "/creator/fragments/modal :: itemDelConfirmation";
    }

    /*
    Delete item
     */
    @PostMapping(value = "creator/project/delete-item")
    public String deleteItem(Model model, ItemDto itemDto){
        Integer projectId = itemDto.getProjectId();
        Integer itemId = itemDto.getItemId();

        itemService.deleteItemById(itemId);

        ProjectFullInfoEntity fullInfoEntity = projectService.getProjectDetail(projectId);

        List<ItemEntity> itemList = null;

        itemList= itemService.getItemsOfProject(projectId);

        model.addAttribute("items", itemList);
        model.addAttribute("project_dto", fullInfoEntity);
        return "/creator/project-reward :: item-list-div";
    }


//======================================================================================================================
    /*
    *   Open story edit page
     */

    @GetMapping(value = "/creator/project/{projectId}/story")
    public String getProjectStoryForm(Model model, @PathVariable("projectId") Integer projectId){
        ProjectDto dto = new ProjectDto();

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

    @GetMapping(value = "/creator/project/{projectId}/report")
    public String getReport(Model model, @PathVariable("projectId") Integer projectId){
        // initiate
        Map<String, Long> pledgeByWeek = new LinkedHashMap<>();
        // get pledge each week
        List<PledgeReportEntity> listPLedge =  reportService.getPLedgeAmountByWeeks(projectId);
        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);

        OffsetDateTime endDate = projectEntity.getEndDate();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        for(int i = 0; i < listPLedge.size(); i++){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.WEEK_OF_YEAR, listPLedge.get(i).getWeekNumber());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String date = sdf.format(cal.getTime()).toString();
            String formatedDate = date.replace(" ", "/");
            pledgeByWeek.put(formatedDate, listPLedge.get(i).getPledge());
        }
        model.addAttribute("data", pledgeByWeek);

        // get option percentage

        Map<String, String> optionPercentage = new LinkedHashMap<>();
        List<PledgeReportEntity> listOptionPercentage =  reportService.getOptionPercentageByProjectId(projectId);
        for(int i = 0; i< listOptionPercentage.size(); i++){
            optionPercentage.put(listOptionPercentage.get(i).getOptionName(), listOptionPercentage.get(i).getOptionPercent().toString());
        }
        model.addAttribute("optionPercent", optionPercentage);

        // get list of user and pledge that they donated
        List<PledgeReportEntity> listPackage = packageService.getPackageInfoByProjectId(projectId);
        model.addAttribute("packages", listPackage);

        // overview information
        model.addAttribute("investor_count" , projectEntity.getInvestorCount());
        model.addAttribute("pledge", projectEntity.getPledged());

        return "/creator/creator-report";
    }

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

    //PROJECT MANAGEMENT
    //===============================================================================================

    // get admin dashboard
    @GetMapping(value = "/admin")
    public String getAdminIndex(Model model){
        return "redirect:/admin/dashboard";
    }

    // get admin dashboard
    @GetMapping(value = "/admin/dashboard")
    public String getAdminDashboard(Model model){
        List<ProjectFullInfoEntity> listProject = projectService.getAllProjectFullEntityNotWaitingOrEditing();

        List<CategoryEntity>  categoryEntities = categoryService.getAllCategory();
        List<StatusEntity> statusEntities = statusService.getAllStatus();

        Iterator<StatusEntity> it = statusEntities.iterator();
        while(it.hasNext()){
            String i = it.next().getName();
            if(Constant.ProjectStatus.EDITING.getName().equals(i)){
                it.remove();
            }

            if(Constant.ProjectStatus.WAITING.getName().equals(i)){
                it.remove();
            }

            if(Constant.ProjectStatus.REJECT.getName().equals(i)){
                it.remove();
            }
        }

        model.addAttribute("categories", categoryEntities);
        model.addAttribute("status_list", statusEntities);
        model.addAttribute("projects", listProject);



        List<ProjectFullInfoEntity> listPendingProject = projectService.getProjectListByStatus(Constant.ProjectStatus.WAITING.getId());
        List<ProjectFullInfoEntity> listRunningProject = projectService.getProjectListByStatus(Constant.ProjectStatus.RUNNING.getId());


        model.addAttribute("pending_project_count", listPendingProject != null ? listPendingProject.size() : null);
        model.addAttribute("running_project_count", listRunningProject != null ? listRunningProject.size() : null);
        model.addAttribute("creator_count", userRoleService.countUserByRole(Constant.Roles.CREATOR.getId()));
        return "admin/admin-dashboard";
    }

    // search

    @GetMapping(value = "/admin/project/filter")
    public String getAdminProjectFilter(Model model, FormInput formInput ){
        Integer categoryId = formInput.getCategoryId() != null ? Integer.parseInt(formInput.getCategoryId()) : null;
        Integer statusId = formInput.getStatusId() != null ? Integer.parseInt(formInput.getStatusId()) : null;
        String searchInput = formInput.getSearchInput();
        List<Integer> listStatus = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        if(categoryId != null && -1 != categoryId ){
            map.put(Constant.PROJECT_KEY.CATEGORY, categoryId);
        }
        if( statusId != null && -1 != statusId ) {
            listStatus.add(statusId);

        }

        if( null != searchInput && !searchInput.isEmpty()){
            String keyword = "%" + searchInput + "%";
            map.put(Constant.PROJECT_KEY.KEYWORD, keyword);
        }

        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, listStatus);

        List<ProjectFullInfoEntity> list = projectService.filterProjectByConditions(map);
        model.addAttribute("projects", list);
        return "admin/admin-dashboard::project-table";
    }

    // get pending list of project that need approval
    @GetMapping(value = "/admin/project/pending-list")
    public String getAdminPendingList(Model model){
        List<ProjectFullInfoEntity> listProject = projectService.getAllPendingApproveProject();
        List<CategoryEntity>  categoryEntities = categoryService.getAllCategory();

        model.addAttribute("categories", categoryEntities);
        model.addAttribute("projects", listProject);
        return "/admin/pending-approve-project";
    }

    // get pending approve project detail
    @GetMapping(value = "/admin/project/{id}/detail")
    public String getAdminPendingProjectDetail(Model model, @PathVariable("id") Integer projectId){
        ProjectFullInfoEntity projectFullInfoEntity = projectService.getProjectDetail(projectId);

        if(projectFullInfoEntity.getStatusId() != Constant.ProjectStatus.WAITING.getId()){
            return "404";
        }
        projectFullInfoEntity.setThumbnailPath(RELATIVE_PATH+ projectFullInfoEntity.getThumbnailPath());

        ProjectEntity projectEntity = projectService.getProjectEntityById(projectId);
        List<OptionEntity> optionList = null;
        List<ItemEntity> itemList = null;

        if(projectEntity != null){
            optionList = optionService.getOptionListByProjectId(projectId);
            itemList= itemService.getItemsOfProject(projectId);
        }

        List<OptionDto> optionDtos = new ArrayList<>();

        if(optionList != null && !optionList.isEmpty()){
            for(int i = 0; i < optionList.size(); i++){
                OptionEntity option = optionList.get(i);

                List<ItemDtoEntity> listItem = optionItemService.getItemDtoListByProjectIdAndOptionId(projectId, option.getOptionId());
                OptionDto optionDto = new OptionDto(option.getOptionId(),
                        option.getOptionName(),
                        option.getOptionDescription(),
                        option.getFundMin(),
                        listItem,
                        option.getProject().getProjectId(),
                        null);
                optionDtos.add(optionDto);
            }
        }

        StoryEntity story = storyService.getStoryByProjectId(projectId);
        List<CommentEntity> listCom = commentService.getAllCommentsByProjectId(projectId);
        int count = 0;
        Map<String, String> commentMap = new HashMap<>();
        for(int i = 0 ; i< listCom.size(); i++){
            if(listCom.get(i).getIsClose() == Constant.IS_CLOSED.CLOSE.getId()){
                count++;
            }

            if(listCom.get(i).getSectionId() == 1){
                commentMap.put("basicTab", listCom.get(i).getCommentText());
            }else if(listCom.get(i).getSectionId() == 2){
                commentMap.put("rewardTab", listCom.get(i).getCommentText());
            }else if(listCom.get(i).getSectionId() == 3){
                commentMap.put("storyTab", listCom.get(i).getCommentText());
            }
        }

        if(count > 0 ){
            commentMap.put("isClosed", "1");
        }else{
            commentMap.put("isClosed", "0");
        }

        model.addAttribute("options", optionDtos);
        model.addAttribute("items", itemList);
        model.addAttribute("project", projectFullInfoEntity);
        model.addAttribute("story", story);
        model.addAllAttributes(commentMap);

        return "/admin/pending-project-detail";
    }

    // save explaination of reject action
    @PostMapping(value = "/admin/project/save-comment")
    public ResponseEntity<?> saveExplaination(Model model,  FormInput formInput){
        Integer section = formInput.getSection() != null ? Integer.parseInt(formInput.getSection()) : null;
        String comment = formInput.getComment() != null ? formInput.getComment() : null;
        Integer projectId = formInput.getProjectId();
        CommentEntity commentEntity = commentService.getCommentByProjectIdAndSectionId(projectId, section);
        if(commentEntity != null ){
            commentEntity.setCommentText(comment);
            commentEntity.setIsClose(Constant.IS_CLOSED.OPEN.getId());
        }

        try{
            commentService.saveComment(commentEntity);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Error on save comment" , HttpStatus.OK);
        }
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    // Complete review- close comment
    @PostMapping(value = "/admin/project/close-comment")
    public ResponseEntity<?> closeComment(Model model, FormInput formInput){
        Integer projectId = formInput.getProjectId();
        List<CommentEntity> listComment = commentService.getAllCommentsByProjectId(projectId);

        for (CommentEntity comment : listComment) {
            comment.setIsClose(Constant.IS_CLOSED.CLOSE.getId());
            commentService.saveComment(comment);
        }



        return new ResponseEntity<String>("Closed", HttpStatus.OK);
    }

    // Complete review- close comment
    @PostMapping(value = "/admin/project/submit-review-result")
    public ResponseEntity<?> saveReviewResult(Model model, FormInput formInput){
        ProjectEntity projectEntity =  projectService.getProjectEntityById(formInput.getProjectId());
        Integer reviewResult = formInput.getReviewResult();
        StatusEntity st = null;

        if(reviewResult == 1){              // Approve result
            st = statusService.getStatusById(Constant.ProjectStatus.APPROVED.getId());
            projectEntity.setProjectStatus(st);
            projectEntity.setIsEditable(Constant.IS_CLOSED.CLOSE.getId());
            projectService.saveProjectEntity(projectEntity);
        }else if(reviewResult == 2){        // reject
            st = statusService.getStatusById(Constant.ProjectStatus.REJECT.getId());
            projectEntity.setProjectStatus(st);
            projectEntity.setIsEditable(Constant.IS_CLOSED.OPEN.getId());
            projectService.saveProjectEntity(projectEntity);
        }

        return new ResponseEntity<String>("Processed", HttpStatus.OK);
    }



    // USER MANAGEMENT
    //==================================================================================================================

    @GetMapping(value = "/admin/user/list")
    public String getUserList(Model model){

        return null;
    }
}
