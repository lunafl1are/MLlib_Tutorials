//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.netsmartz.mis.service;

import com.netsmartz.mis.entity.Login;
import com.netsmartz.mis.exception.ExceptionConstant;
import com.netsmartz.mis.request.AuthenticationRequest;
import com.netsmartz.mis.response.AuthenticationResponse;
import com.netsmartz.mis.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, LoginService loginService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String jwtToken = null;

        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = this.loginService.loadUserByUsername(authentication.getName());
            jwtToken = this.jwtService.generateToken(userDetails);
        } catch (BadCredentialsException var5) {
            throw new BadCredentialsException("Bad credentials.");
        } catch (DisabledException var6) {
            throw new DisabledException("User is disabled. Please reach to admin.");
        }

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public Login getCurrentLoginUser(){
        Optional<Login> userOpt = SecurityUtils.getCurrentLoginUser().flatMap(loginService::findOneByEmail);
        if(userOpt.isEmpty()) throw new UsernameNotFoundException(ExceptionConstant.USER_NOT_FOUND);
        return userOpt.get();
    }
}
