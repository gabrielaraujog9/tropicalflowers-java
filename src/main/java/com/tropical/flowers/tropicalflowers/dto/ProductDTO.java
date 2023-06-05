package com.tropical.flowers.tropicalflowers.dto;

import lombok.Data;

@Data
public class ProductDTO {
  private String name;
  private String image;
  private String description;
  private Double price;
  private int stock;
}
