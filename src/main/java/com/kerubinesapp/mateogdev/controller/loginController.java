package com.kerubinesapp.mateogdev.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping("/login")
    public String mostrarFormularioLogin(){
        return "login"; // Esto mapea a src/main/resources/templates/login.html
    }


}
