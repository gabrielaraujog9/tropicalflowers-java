package com.tropical.flowers.tropicalflowers.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {
  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private String id;
  @Column(name = "quantity_items", nullable = false)
  private int quantityItems;
  @ManyToOne
  @JoinColumn(name = "productId", unique = true)
  private Product product;
  @ManyToOne
  @JoinColumn(name = "shoppingCartId", unique = true)
  private ShoppingCart cart;
  @ManyToOne
  @JoinColumn(name = "orderId", unique = true)
  private Order order;
  public ShoppingCartItem() {
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getQuantityItems() {
    return quantityItems;
  }
  public void setQuantityItems(int quantityItems) {
    this.quantityItems = quantityItems;
  }
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  public ShoppingCart getCart() {
    return cart;
  }
  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }
  public Order getOrder() {
    return order;
  }
  public void setOrder(Order order) {
    this.order = order;
  }
}
