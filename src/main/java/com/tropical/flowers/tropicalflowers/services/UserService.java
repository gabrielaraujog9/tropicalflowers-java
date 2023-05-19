package com.tropical.flowers.tropicalflowers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
  }
  
  public User createUser(User user) {
    // Realize validações ou lógica de negócio antes de criar o usuário
    return userRepository.save(user);
  }
}
