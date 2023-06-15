package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.ShoppingCart;
import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.ShoppingCartRepository;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;
import com.tropical.flowers.tropicalflowers.services.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void criar(String idCliente) {
    Optional<User> user = userRepository.findById(idCliente);
    ShoppingCart cart = new ShoppingCart();
    cart.setUser(user.get());
    shoppingCartRepository.save(cart);
  }
  
}
