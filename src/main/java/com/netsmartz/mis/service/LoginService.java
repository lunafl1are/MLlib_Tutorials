package com.netsmartz.mis.service;

import com.netsmartz.mis.entity.Login;
import com.netsmartz.mis.entity.Role;
import com.netsmartz.mis.repository.LoginRepository;
import com.netsmartz.mis.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {


    private final LoginRepository loginRepository;

    private static final String ENTITY_NAME = " PM credentials";

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<Login> login = loginRepository.findOneByEmail(username);
        if(login.isEmpty()) throw new UsernameNotFoundException(Constant.EMAIL_NOT_FOUND);


        return new org.springframework.security.core.userdetails.User(login.get().getEmail(), login.get().getPassword(), login.get().isEnabled(),true,true,true, getAuthorities(login.get()));
    }


    private static Collection<? extends GrantedAuthority> getAuthorities(Login login){

        return AuthorityUtils.createAuthorityList(login.getRole().getName());
    }

    public Optional<Login> findOneByEmail(String email){
        return  loginRepository.findOneByEmail(email);
    }

    public  Login findByEmail(String email){
        Optional<Login> login = findOneByEmail(email);
        if(login.isEmpty()) throw new UsernameNotFoundException(Constant.EMAIL_NOT_FOUND);
        return login.get();
    }

    public Login saveAndUpdate(Login login){
        try {
            return loginRepository.save(login);
        }
        catch(Exception e)
        {
            throw new NullPointerException(Constant.FAILED_TO_SAVE_OR_UPDATE + ENTITY_NAME);
        }
    }


}
