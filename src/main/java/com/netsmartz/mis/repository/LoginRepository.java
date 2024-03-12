package com.netsmartz.mis.repository;

import com.netsmartz.mis.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {

//  Optional<Login> findByEmail(String email);


 /* @Transactional
  @Modifying
  @Query("update Login u set u.password = :password where u.email = :email")
  void updatePassword(String password,String email);*/


  Optional<Login> findOneByEmail(String email);


}
