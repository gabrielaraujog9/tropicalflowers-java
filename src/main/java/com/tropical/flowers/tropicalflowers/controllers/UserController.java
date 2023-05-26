package com.tropical.flowers.tropicalflowers.controllers;

import com.tropical.flowers.tropicalflowers.dto.ApiResponse;
import com.tropical.flowers.tropicalflowers.dto.CadrastroResquest;
import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.services.ClienteService;
import com.tropical.flowers.tropicalflowers.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

@RestController
public class UserController {
  @Autowired
  private ClienteService _clienteService;

 
  
  @PostMapping("/register")
  public ResponseEntity<ApiResponse> cadastrar(@RequestBody CadrastroResquest request) {
    _clienteService.create(new User(request.getName(), request.getEmail(), request.getPassword(), request.getCpf()));
    return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
  }



  
  @PostMapping("/register/administrador")
  public ResponseEntity<User> cadastrarAdministrador(@RequestBody User userCreate) {

    return new ResponseEntity<User>(_userService.createUser(userCreate), HttpStatus.OK);
  }
  

}
