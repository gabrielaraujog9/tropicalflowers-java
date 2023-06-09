package com.tropical.flowers.tropicalflowers.dto;

import com.tropical.flowers.tropicalflowers.models.Product;

import lombok.Data;

@Data
public class CreateProductResponse {
  private String message;
  private Product product;
  public CreateProductResponse(String message) {
    this.message = message;
  }
  public CreateProductResponse(String message, Product product) {
    this.message = message;
    this.product = product;
  }
}
