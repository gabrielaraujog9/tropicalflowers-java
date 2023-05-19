package com.tropical.flowers.tropicalflowers.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private String id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "created_at", nullable = false)
  private Date createdAt;
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "cpf", nullable = false)
  private String cpf;
  @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
  private ShoppingCart cart;
  @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
  private List<Order> orders;

  public User() {
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

 
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  public Date getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  
  public Date getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }


  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = new Date();
  }

  @PrePersist
  public void prePersist() {
    final Date atual = new Date();
    createdAt = atual;
    updatedAt = atual;
  }
  public ShoppingCart getCart() {
    return cart;
  }

  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }
  
  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
  
}
