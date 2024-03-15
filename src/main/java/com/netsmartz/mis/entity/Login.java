package com.netsmartz.mis.entity;

import com.netsmartz.mis.base.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Login  {


    @Id
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    private Role role;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Privileges> privileges;
}

