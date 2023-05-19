package com.tropical.flowers.tropicalflowers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String>{
  
}
