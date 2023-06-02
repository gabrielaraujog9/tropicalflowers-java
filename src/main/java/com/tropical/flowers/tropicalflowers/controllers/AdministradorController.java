package com.tropical.flowers.tropicalflowers.controllers;

import com.tropical.flowers.tropicalflowers.dto.ApiResponse;
import com.tropical.flowers.tropicalflowers.dto.AtualizarSenhaRequest;
import com.tropical.flowers.tropicalflowers.dto.CadrastroRequest;
import com.tropical.flowers.tropicalflowers.dto.LoginRequest;
import com.tropical.flowers.tropicalflowers.models.Administrador;
import com.tropical.flowers.tropicalflowers.services.AdministradorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

@RestController
public class AdministradorController {

  @Autowired
  private AdministradorService administradorService;

  @GetMapping("/administrador")
  public ResponseEntity<List<Administrador>> buscarTodos() throws Exception {
    return ResponseEntity.ok().body(administradorService.buscarTodos());
  }

 @GetMapping("/administrador/{id}")
  public ResponseEntity<Administrador> pegarPorId(@PathVariable("id") String id) throws Exception{
    return ResponseEntity.ok().body(administradorService.pegarPorId(id));
  }
  
  @PostMapping("/administrador")
  public ResponseEntity<ApiResponse> cadastrar(@RequestBody CadrastroRequest request) throws Exception {
    try{
      administradorService.cadastrar(request.getName(), request.getEmail(), request.getPassword(), request.getCpf());
      return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @PostMapping("/administrador/login")
  public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) throws Exception {
    try{
      String token = administradorService.login(request.getEmail(), request.getPassword());
      return ResponseEntity.ok().body(new ApiResponse("Autenticação feita com sucesso!", token));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @PutMapping("/administrador")
  public ResponseEntity<ApiResponse> atualizarSenha(@RequestBody AtualizarSenhaRequest request){
    try{
      administradorService.atualizarSenha(request);
      return ResponseEntity.ok().body(new ApiResponse("Senha atualizada com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @DeleteMapping("/administrador/{id}")
  public ResponseEntity<ApiResponse> deletarPorId(@PathVariable("id") String id) throws Exception{
    try{
      administradorService.deletarPorId(id);
      return ResponseEntity.ok().body(new ApiResponse("Usuário deletado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }
}
