package com.tropical.flowers.tropicalflowers.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private String id;
  @Column(name = "status", columnDefinition = "integer default 0")
  private int status;
  @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
  private List<ShoppingCartItem> items;
  @OneToOne
  @JoinColumn(name = "userId", unique = true)
  private User user;
  public Order() {
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public List<ShoppingCartItem> getItems() {
    return items;
  }
  public void setItems(List<ShoppingCartItem> items) {
    this.items = items;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
}
