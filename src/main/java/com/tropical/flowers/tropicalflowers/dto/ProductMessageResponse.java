package com.tropical.flowers.tropicalflowers.dto;

import lombok.Data;

@Data
public class ProductMessageResponse {
  private String message;
  public ProductMessageResponse(String message) {
    this.message = message;
  }
}
