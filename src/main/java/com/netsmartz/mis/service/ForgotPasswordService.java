package com.netsmartz.mis.service;

import com.netsmartz.mis.dto.EmailDTO;
import com.netsmartz.mis.entity.Login;
import com.netsmartz.mis.utils.DefaultPasswordGenerator;
import com.netsmartz.mis.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {


    @Autowired
    private final LoginService loginService;

    @Autowired
    private final DefaultPasswordGenerator defaultPasswordGenerator;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final SendEmail sendEmail;


    public ForgotPasswordService(LoginService loginService, DefaultPasswordGenerator defaultPasswordGenerator, BCryptPasswordEncoder bCryptPasswordEncoder, SendEmail sendEmail) {
        this.loginService = loginService;
        this.defaultPasswordGenerator = defaultPasswordGenerator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendEmail = sendEmail;
    }

    public HttpStatus forgot(String email){

         Login login = loginService.findByEmail(email);
         String password= defaultPasswordGenerator.generateSecurePassword();
         login.setPassword(bCryptPasswordEncoder.encode(password));
          loginService.saveAndUpdate(login);

            return sendEmail.sendEmail(login.getEmail(),
                    "Password for BYT-MIS portal",
                    "Please use this password logging into BYT-MIS portal : "+password);
       }
    }

