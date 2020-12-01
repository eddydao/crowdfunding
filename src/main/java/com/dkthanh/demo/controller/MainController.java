package com.dkthanh.demo.controller;

import com.dkthanh.demo.domain.NewUserDTO;
import com.dkthanh.demo.domain.UserEntity;
import com.dkthanh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

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
            return "/404";
        }

        UserEntity newUser = new UserEntity();
        try{
            newUser = userService.saveUser(newUserDTO);
        }catch (Exception e){
            return "/register";
        }
        return "temp-result";
    }

    // open login form
    @GetMapping(value = "/login-page")
    public String login() {
        return "/login-page";
    }

    /*
    * ===========================================
    * */

//    index page
    @GetMapping(value = "/index")
    public String index(){
        return "/index";
    }

    @GetMapping(value = "/")
    public String getHomePage(){
        return "redirect:/index";
    }


}
