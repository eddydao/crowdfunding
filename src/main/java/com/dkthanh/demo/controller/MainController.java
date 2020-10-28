package com.dkthanh.demo.controller;

import com.dkthanh.demo.domain.NewUserDTO;
import com.dkthanh.demo.domain.Role;
import com.dkthanh.demo.domain.User;
import com.dkthanh.demo.service.UserService;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.Validate;

import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
//    Open register page
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String openRegisterForm(Model model){
        NewUserDTO newUserDTO = new NewUserDTO();
        model.addAttribute("newUserForm", newUserDTO);
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewAccount(Model model, @ModelAttribute("newUserForm") @Validated NewUserDTO newUserDTO, BindingResult result, final RedirectAttributes redirectAttributes){
        // Validate result
        if (result.hasErrors()) {
            return "/404";
        }

        Role role = new Role();
        role.setRoleName(Constant.ROLES.INVESTOR);

        User newUser = new User();
        try{
//            newUser =
        }catch (Exception e){
            return "/register";
        }

        return null;
    }
}
