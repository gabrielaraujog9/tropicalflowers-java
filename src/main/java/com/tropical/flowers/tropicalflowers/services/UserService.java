package com.tropical.flowers.tropicalflowers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;
  
  public User createUser(User user) {
      
    return userRepository.save(user);
  }
}
