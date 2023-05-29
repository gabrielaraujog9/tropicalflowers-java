package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.Cliente;
import com.tropical.flowers.tropicalflowers.repositories.ClienteRepository;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;
import com.tropical.flowers.tropicalflowers.security.LoginService;
import com.tropical.flowers.tropicalflowers.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
  @Autowired
  private ClienteRepository clienteRepository;

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
    Cliente cliente = new Cliente();
    cliente.setName(name);
    cliente.setEmail(email);
    cliente.setCpf(cpf);
    cliente.setPassword(passwordEncoder.encode(password));
    
    clienteRepository.save(cliente);
  }


  @Override
  public List<Cliente> buscarTodos() {
    return clienteRepository.findAll();
  }


  @Override
  public String login(String email, String password) throws Exception {
    String token = loginService.login(email, password);
    return token;
  }

}