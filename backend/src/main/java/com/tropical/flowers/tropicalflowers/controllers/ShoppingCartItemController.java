package com.tropical.flowers.tropicalflowers.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.flowers.tropicalflowers.dto.ItemRequest;
import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.services.ShoppingCartItemService;
import com.tropical.flowers.tropicalflowers.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
@Tag(name = "Itens do Carrinho")
public class ShoppingCartItemController {
  
  @Autowired
  private ShoppingCartItemService itemService;

  @Autowired
  private UserService userService;

  @Operation(summary = "Adicionar Produto aos items do carrinho")
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping("/add")
  public ResponseEntity<String> adicionar(@RequestHeader Map<String, String> headers, ItemRequest item){
    try{
      String token = headers.get("authorization").toString();
      User user = userService.pegarPorToken(token);
      String message = itemService.criarItem(item, user);
      return ResponseEntity.ok().body(message);
    }
    catch(Exception e){
      return ResponseEntity.ok().body(e.getMessage());
    }
  }

  @Operation(summary = "Deletar itens produto do carrinho")
  @SecurityRequirement(name = "Bearer Authentication")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deletar(@PathVariable String id){
    try{
      String message = itemService.deletar(id);
      return ResponseEntity.ok().body(message);
    }
    catch(Exception e){
      return ResponseEntity.ok().body(e.getMessage());
    }
  }

  @Operation(summary = "Atualizar quantidade de produto dos itens")
  @SecurityRequirement(name = "Bearer Authentication")
  @PutMapping("/update/{id}")
  public ResponseEntity<String> atualizar(@PathVariable String id, ItemRequest item){
    try{
      String message = itemService.atualizar(id, item);
      return ResponseEntity.ok().body(message);
    }
    catch(Exception e){
      return ResponseEntity.ok().body(e.getMessage());
    }
  }
}
