package com.dkthanh.demo.controller;

import com.dkthanh.demo.domain.CategoryEntity;
import com.dkthanh.demo.domain.NewUserDTO;
import com.dkthanh.demo.domain.UserEntity;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import com.dkthanh.demo.service.CategoryService;
import com.dkthanh.demo.service.ProjectService;
import com.dkthanh.demo.service.UserService;
import com.dkthanh.demo.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProjectService projectService;

    /*
     *  Common function
     * ===========================================
     */

    // load thumbnail image
    @RequestMapping(value = { "/project/image/{project_id}" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @PathVariable("project_id") int projectId) throws IOException {
        String path = projectService.getProjectDetail(projectId).getMaterialThumbnailPath();
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(WebUtil.extractByte(path));
        response.getOutputStream().close();
    }

    // return temp result for testing
    @GetMapping(value = "/temp-result")
    public String tempResult(){
        return "temp-result";
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
    @GetMapping(value = "/login")
    public String login() {
        return "login-page";
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
    /*
     *  index page function
     * ===========================================
     */

    //    index page
    @GetMapping(value = "/index")
    public String index(Model model){
        List<CategoryEntity> categoryEntityList = categoryService.getAllCategory();
        List<ProjectFullInfoEntity> popularProjects = projectService.getPopularProjects();
        ProjectFullInfoEntity recommendedProject = projectService.getRecommendedProject();
        model.addAttribute("category_list", categoryEntityList);
        model.addAttribute("recommended_projects", popularProjects);
        model.addAttribute("recommended_project", recommendedProject);
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String getHomePage(){
        return "index";
    }

    //  load project detail page
    @GetMapping(value = "/project/{id}")
    public String getProjectDetailPage(Model model){
        return null;
    }


    /*
     *  All project page function
     * ===========================================
     */


}
