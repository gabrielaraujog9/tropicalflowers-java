package com.tropical.flowers.tropicalflowers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import static com.tropical.flowers.tropicalflowers.models.Perfil.ADMINISTRADOR;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Administrador extends User{
  
  public Perfil getPerfil() {
    return ADMINISTRADOR;
  }
  
}
