package com.tropical.flowers.tropicalflowers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  private static final String headerPrefix = "Bearer ";

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JWTService jwtService;

  public String login(String email, String senha) {
    try{
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = headerPrefix + jwtService.gerarToken(authentication);
      return token;
    }catch(Exception e){
      System.out.println("####################################" + e.getMessage() + "####################################");
      return e.getMessage();
    }
  }
}
