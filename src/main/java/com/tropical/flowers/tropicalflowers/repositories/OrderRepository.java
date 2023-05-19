package com.tropical.flowers.tropicalflowers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.Order;

public interface OrderRepository extends JpaRepository<Order, String>{
  
}
