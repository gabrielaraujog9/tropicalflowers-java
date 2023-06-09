package com.tropical.flowers.tropicalflowers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, String>{

  Optional<Administrador> findByEmail(String email);
  
}
