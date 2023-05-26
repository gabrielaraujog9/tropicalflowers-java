package com.tropical.flowers.tropicalflowers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.Cliente;
import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;

@Service
public class ClienteService {
  @Autowired
  private UserRepository userRepository;

  public User create(User user) throws Exception {
    List<User> userList = userRepository.findByEmailOrCpf(user.getEmail(), user.getCpf());
    if(userList.size() > 0){
      throw new Exception("Usuário com E-mail ou CPF já cadastrado!");
    }
    Cliente cliente = new Cliente();
    cliente.setName(user.getName());
    cliente.setEmail(user.getEmail());
    cliente.setCpf(user.getCpf());
    cliente.setPassword(passwordEncoder.encode(user.getPassword()));
    
    return userRepository.save(user);
  }
}
