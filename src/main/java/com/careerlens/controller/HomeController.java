package com.careerlens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "index";

    }

    @GetMapping("/register")
    public String register() {

        return "register";

    }

    @GetMapping("/login")
    public String login() {

        return "login";

    }

    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard";

    }

    @GetMapping("/analysis")
    public String analysis() {

        return "analysis";

    }

}