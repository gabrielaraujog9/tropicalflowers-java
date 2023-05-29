package com.tropical.flowers.tropicalflowers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
  
}
