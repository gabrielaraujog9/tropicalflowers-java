package com.tropical.flowers.tropicalflowers.dto;

import com.tropical.flowers.tropicalflowers.models.Administrador;

import lombok.Data;

@Data
public class AdministradorResponse {
  private String message;
  private Administrador administrador;
  public AdministradorResponse(String message) {
    this.message = message;
  }
  public AdministradorResponse(Administrador administrador) {
    this.administrador = administrador;
  };
}
