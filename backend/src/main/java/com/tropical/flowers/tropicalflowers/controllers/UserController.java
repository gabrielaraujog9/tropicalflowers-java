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

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


  @GetMapping("/user")
  public ResponseEntity<List<User>> buscarTodos(){
    return ResponseEntity.ok().body(userService.buscarTodos());
  }

  @GetMapping("/user/token")
  public ResponseEntity<User> pegarPorToken(@RequestHeader Map<String, String> headers) throws Exception{
    String token = headers.get("authorization").toString();
    User user = userService.pegarPorToken(token);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping("user/login")
  public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) throws Exception {
    try{
      String token = userService.login(request.getEmail(), request.getPassword());
      
      return ResponseEntity.ok().body(new ApiResponse("Autenticação feita com sucesso!", token));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

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
