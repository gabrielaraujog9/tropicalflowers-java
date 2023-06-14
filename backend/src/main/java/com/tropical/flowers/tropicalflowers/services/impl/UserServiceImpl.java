package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;
import com.tropical.flowers.tropicalflowers.security.JWTService;
import com.tropical.flowers.tropicalflowers.security.LoginService;
import com.tropical.flowers.tropicalflowers.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private LoginService loginService;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public String login(String email, String password) throws Exception {
    String token = loginService.login(email, password);
    return token;
  }

  @Override
  public User pegarPorToken(String token) throws Exception {
    try{
      Optional<String> idCliente = jwtService.getUserId(token.split(" ")[1]);
      if(!idCliente.isPresent()){
        throw new Exception("ID do usuário não encontrado");
      }
      Optional<User> user = userRepository.findById(idCliente.get());
      if(!user.isPresent()){
        throw new Exception("Usuário não encontrado");
      }
      return user.get();
    }catch(Exception e){
      throw new Exception("Erro interno no servidor");
    }
    
  }

  @Override
  public List<User> buscarTodos() {
    return userRepository.findAll();
  }

  @Override
  public void deletarPorId(String id) {
    try{
      userRepository.deleteById(id);
    }catch(Exception e){
      throw e;
    }
  }
  
}
