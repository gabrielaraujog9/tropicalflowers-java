package com.tropical.flowers.tropicalflowers.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.Administrador;
import com.tropical.flowers.tropicalflowers.repositories.AdministradorRepository;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;
import com.tropical.flowers.tropicalflowers.services.AdministradorService;

@Service
public class AdministradorServiceImpl implements AdministradorService {
  @Autowired
  private AdministradorRepository administradorRepository;

  @Autowired
  private UserRepository userRepository;

  //@Autowired
  private PasswordEncoder passwordEncoder;
  
  @Override
  public void cadastrar(String name, String email, String password, String cpf) throws Exception {
    if(userRepository.findByEmail(email).isPresent()){
      throw new Exception("Usuário já cadastrado!");
    }
    Administrador adm = new Administrador();
    adm.setName(name);
    adm.setEmail(email);
    adm.setCpf(cpf);
    adm.setPassword(passwordEncoder.encode(password));
    
    administradorRepository.save(adm);
  }
  
}
