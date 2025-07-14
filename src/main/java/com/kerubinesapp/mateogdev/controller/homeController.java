package com.kerubinesapp.mateogdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/home")
    public String inicio(){
        return "index"; // Esto mapea a src/main/resources/templates/index.html
    }
}
