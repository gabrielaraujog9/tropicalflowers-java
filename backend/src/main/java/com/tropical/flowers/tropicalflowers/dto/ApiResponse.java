package com.tropical.flowers.tropicalflowers.dto;

import lombok.Data;

@Data
public class ApiResponse {

  private String message;

  private String token;

  public ApiResponse(String message, String token) {
    this.message = message;
    this.token = token;
  }

  public ApiResponse(String message) {
    this.message = message;
  }

  
}
