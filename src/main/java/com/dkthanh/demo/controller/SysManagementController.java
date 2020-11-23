package com.dkthanh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysManagementController {

    @GetMapping(value = "admin/dashboard")
        public String getDashboard(){
            return "admin/dashboard";
        }
}
