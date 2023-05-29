package com.tropical.flowers.tropicalflowers.controllers;

import com.tropical.flowers.tropicalflowers.dto.ApiResponse;
import com.tropical.flowers.tropicalflowers.dto.CadrastroResquest;
import com.tropical.flowers.tropicalflowers.dto.LoginRequest;
import com.tropical.flowers.tropicalflowers.models.Cliente;
import com.tropical.flowers.tropicalflowers.services.AdministradorService;
import com.tropical.flowers.tropicalflowers.services.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

@RestController
public class UserController {
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private AdministradorService administradorService;

  @GetMapping("/user")
  public List<Cliente> buscarTodos() throws Exception {
    return clienteService.buscarTodos();
  }

  @PostMapping("/user")
  public ResponseEntity<ApiResponse> cadastrar(@RequestBody CadrastroResquest request) throws Exception {
    try{
      clienteService.cadastrar(request.getName(), request.getEmail(), request.getPassword(), request.getCpf());
      return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @PostMapping("/user/login")
  public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) throws Exception {
    try{
      String token = clienteService.login(request.getEmail(), request.getPassword());
      return ResponseEntity.ok().body(new ApiResponse("Autenticação feita com sucesso!", token));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }
  
  @PostMapping("/user/register/administrador")
  public ResponseEntity<ApiResponse> cadastrarAdministrador(@RequestBody CadrastroResquest request) throws Exception {
    administradorService.cadastrar(request.getName(), request.getEmail(), request.getPassword(), request.getCpf());
    return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
  }
  

}
