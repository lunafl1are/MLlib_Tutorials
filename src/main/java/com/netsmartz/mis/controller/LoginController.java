package com.netsmartz.mis.controller;

import com.netsmartz.mis.entity.Login;
import com.netsmartz.mis.request.AuthenticationRequest;
import com.netsmartz.mis.response.AuthenticationResponse;
import com.netsmartz.mis.service.AuthenticationService;
import com.netsmartz.mis.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
/*@RequestMapping(Constant.BASE_URL+"auth")*/

@RequestMapping("mis/v1/login")
@RequiredArgsConstructor
public class LoginController {

  private final AuthenticationService service;

  private final ForgotPasswordService forgotPassword;

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
      return ResponseEntity.ok(service.authenticate(request));

  }

  @GetMapping("/forgot")
    public ResponseEntity<HttpStatus> forgotPassword(@RequestParam String email) {

    return ResponseEntity.ok(forgotPassword.forgot(email));
  }



 /* @GetMapping("/test")
  public void testing(){
//    service.getCurrentLoginUser();

    System.out.println(SecurityContextHolder.getContext().getAuthentication());

  }*/
}
