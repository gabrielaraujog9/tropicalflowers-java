package com.tropical.flowers.tropicalflowers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.ShoppingCart;
import com.tropical.flowers.tropicalflowers.models.User;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String>{
  public Optional<ShoppingCart> findByUser(User user);
}
