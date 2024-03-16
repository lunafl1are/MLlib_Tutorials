package com.netsmartz.mis.controller;

import com.netsmartz.mis.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class LoginAuthenticationController {

    @Autowired
    AuthenticationService service;

    @GetMapping("/home")
    public String home()
    {
        return "welcome to BYT-MIS portal";
    }

    @GetMapping("/change")
    public void changePassword(){

        System.out.println(service.getCurrentLoginUser());
    }
}
