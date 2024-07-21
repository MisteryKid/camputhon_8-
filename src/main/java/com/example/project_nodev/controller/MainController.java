package com.example.project_nodev.controller;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {


    @GetMapping("/")
    @RequestMapping("/cat")

    public String main(){
        return "main";
    }
}
