package com.dkthanh.demo.controller;

import com.dkthanh.demo.service.CategoryService;
import com.dkthanh.demo.service.ProjectService;
import com.dkthanh.demo.service.TeamService;
import com.dkthanh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "admin/dashboard")
        public String getDashboard(){
            return "admin/dashboard";
        }

    /*
    /creator/project/list
    /creator/create-project
    /creator/project/id
    /creator/project/save
    /creator/project/edit/id
    /creator/project/delete/id
    /creator/team/user
     */
    // creator project/list
    @GetMapping(value = "/creator/project/list")
    public String getCreatorProjectList(Model model){
//        projectService.ge
        return "admin/creator-project-list";
    }

    /*
    admin/project/list
    admin/project/id
    admin/project/approval
    admin/category/list
    admin/user/list
    admin/
     */
}
