package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.AtualizarSenhaRequest;
import com.tropical.flowers.tropicalflowers.models.Administrador;
import com.tropical.flowers.tropicalflowers.repositories.AdministradorRepository;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;
import com.tropical.flowers.tropicalflowers.security.LoginService;
import com.tropical.flowers.tropicalflowers.services.AdministradorService;

@Service
public class AdministradorServiceImpl implements AdministradorService {
  @Autowired
  private AdministradorRepository administradorRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private LoginService loginService;

  
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

  @Override
  public List<Administrador> buscarTodos() {
    return administradorRepository.findAll();
  }

  @Override
  public String login(String email, String password) {
    String token = loginService.login(email, password);
    return token;
  }

  @Override
  public Administrador pegarPorId(String id) throws Exception {
    Optional<Administrador> administrador = administradorRepository.findById(id);
    if(!administrador.isPresent()){
      throw new Exception("Não existe usuário com este ID");
    }
    return administrador.get();
  }

  @Override
  public void atualizarSenha(AtualizarSenhaRequest request) throws Exception {
    Optional<Administrador> administrador = administradorRepository.findByEmail(request.getEmail());
    if(!administrador.isPresent()){
      throw new Exception("Usuário não cadastrado!");
    }
    String newPassword = passwordEncoder.encode(request.getPassword());
    
    administrador.get().setPassword(newPassword);

    administradorRepository.save(administrador.get());
  }

  @Override
  public void deletarPorId(String id) {
    try{
      administradorRepository.deleteById(id);
    }catch(Exception e){
      throw e;
    }
  }
  
}
