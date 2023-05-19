package com.tropical.flowers.tropicalflowers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
  
}
