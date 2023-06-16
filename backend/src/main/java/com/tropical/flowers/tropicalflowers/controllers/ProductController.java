package com.tropical.flowers.tropicalflowers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.flowers.tropicalflowers.dto.CreateProductResponse;
import com.tropical.flowers.tropicalflowers.dto.ProductDTO;
import com.tropical.flowers.tropicalflowers.dto.ProductMessageResponse;
import com.tropical.flowers.tropicalflowers.models.Product;
import com.tropical.flowers.tropicalflowers.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Produto")
public class ProductController {
  @Autowired
  ProductService productService;

  @Operation(summary = "Listar produtos")
  @GetMapping("/product")
  public ResponseEntity<List<Product>> buscarTodos() {
    return ResponseEntity.ok().body(productService.buscarTodos());
  }

  @Operation(summary = "Buscar por ID")
  @GetMapping("/product/{id}")
  public ResponseEntity<Product> pegarPorId(@PathVariable("id") String id) throws Exception{
    return ResponseEntity.ok().body(productService.pegarPorId(id));
  }

  @Operation(summary = "Cadastrar Produto")
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping("/product")
  public ResponseEntity<CreateProductResponse> cadastrar(@RequestBody ProductDTO prodRequest){
    try{
      Product produto = productService.cadastrar(prodRequest);
      return ResponseEntity.created(null).body(new CreateProductResponse("Produto criado com sucesso!", produto));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new CreateProductResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Atualizar Produto")
  @SecurityRequirement(name = "Bearer Authentication")
  @PutMapping("/product/{id}")
  public ResponseEntity<ProductMessageResponse> atualizarPorId(@PathVariable("id") String id, @RequestBody ProductDTO prodRequest) throws Exception {
    try{
      productService.atualizarPorId(id, prodRequest);
      return ResponseEntity.created(null).body(new ProductMessageResponse("Produto atualizado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.created(null).body(new ProductMessageResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Deletar por ID")
  @SecurityRequirement(name = "Bearer Authentication")
  @DeleteMapping("/product/{id}")
  public ResponseEntity<ProductMessageResponse> deletarPorId(@PathVariable("id") String id) throws Exception {
    try{
      productService.deletarPorId(id);
      return ResponseEntity.created(null).body(new ProductMessageResponse("Produto deletado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.created(null).body(new ProductMessageResponse(e.getMessage()));
    }
  }
}
