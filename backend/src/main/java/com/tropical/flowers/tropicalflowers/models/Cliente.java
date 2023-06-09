package com.tropical.flowers.tropicalflowers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import static com.tropical.flowers.tropicalflowers.models.Perfil.CLIENTE;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Cliente extends User{

  public Perfil getPerfil() {
    return CLIENTE;
  }
  
}
