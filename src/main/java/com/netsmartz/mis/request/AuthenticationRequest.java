package com.netsmartz.mis.request;

import com.netsmartz.mis.entity.Privileges;
import com.netsmartz.mis.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  private String email;
 private  String password;

}
