package com.tropical.flowers.tropicalflowers.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;

import java.util.Optional;
import java.util.function.Supplier;

public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
   User user = getUser(() -> userRepository.findByEmail(email));
   return UserPrincipal.create(user);
  }

  public UserDetails loadUserById(String id) {
    User user = getUser(() -> userRepository.findById(id));
    return UserPrincipal.create(user);
}

private User getUser(Supplier<Optional<User>> supplier) {
    return supplier.get().orElseThrow(() ->
            new UsernameNotFoundException("Usuário não cadastrado")
    );
}

}
