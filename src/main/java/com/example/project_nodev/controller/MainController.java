package com.example.project_nodev.controller;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cat")
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/location")
    public String location(){

        return "location";
    }

    @GetMapping("/hospital")
    public String hospital(){

        return "hospital";
    }

    @GetMapping("/donate")
    public String donate(){

        return "donate";
    }

    @GetMapping("/explain")
    public String explain(){

        return "explain";
    }

}
