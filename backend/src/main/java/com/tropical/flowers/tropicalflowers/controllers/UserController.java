package com.tropical.flowers.tropicalflowers.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.flowers.tropicalflowers.dto.ApiResponse;
import com.tropical.flowers.tropicalflowers.dto.LoginRequest;
import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Usuário")
public class UserController {
  @Autowired
  private UserService userService;

  @Operation(summary = "Listar todos os usuários")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping("/user")
  public ResponseEntity<List<User>> buscarTodos(){
    return ResponseEntity.ok().body(userService.buscarTodos());
  }
  @Operation(summary = "Buscar usuário pelo token")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping("/user/token")
  public ResponseEntity<User> pegarPorToken(@RequestHeader Map<String, String> headers) throws Exception{
    String token = headers.get("authorization").toString();
    User user = userService.pegarPorToken(token);
    return ResponseEntity.ok().body(user);
  }

  @Operation(summary = "Fazer login de usuário")
  @PostMapping("user/login")
  public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) throws Exception {
    try{
      String token = userService.login(request.getEmail(), request.getPassword());
      
      return ResponseEntity.ok().body(new ApiResponse("Autenticação feita com sucesso!", token));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Deletar usuário por ID")
  @SecurityRequirement(name = "Bearer Authentication")
  @DeleteMapping("/user/{id}")
  public ResponseEntity<ApiResponse> deletarPorId(@PathVariable("id") String id) throws Exception{
    try{
      userService.deletarPorId(id);
      return ResponseEntity.ok().body(new ApiResponse("Usuário deletado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }
}
