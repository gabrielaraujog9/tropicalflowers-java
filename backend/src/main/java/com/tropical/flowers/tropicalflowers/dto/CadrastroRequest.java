package com.tropical.flowers.tropicalflowers.dto;

import lombok.Data;

@Data
public class CadrastroRequest {
  private String name;
  private String email;
  private String password;
  private String cpf;
}
