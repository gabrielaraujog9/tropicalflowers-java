package com.tropical.flowers.tropicalflowers.controllers;

import com.tropical.flowers.tropicalflowers.dto.AdministradorResponse;
import com.tropical.flowers.tropicalflowers.dto.ApiResponse;
import com.tropical.flowers.tropicalflowers.dto.AtualizarSenhaRequest;
import com.tropical.flowers.tropicalflowers.dto.CadrastroRequest;
import com.tropical.flowers.tropicalflowers.services.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Administrador")
public class AdministradorController {

  @Autowired
  private AdministradorService administradorService;

  @Operation(summary = "Buscar por ID", description = "utilizado para buscar o administrador, é necessário autenticação para conseguir concluir a ação.")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping("/administrador/{id}")
  public ResponseEntity<AdministradorResponse> pegarPorId(@PathVariable("id") String id) throws Exception{
    try{
      return ResponseEntity.ok().body(new AdministradorResponse(administradorService.pegarPorId(id)));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new AdministradorResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Criar adm", description = "utilizado para criar um administrador, é necessário autenticação para conseguir concluir a ação.")
  @SecurityRequirement(name = "Bearer Authentication")
  @PostMapping("/administrador")
  public ResponseEntity<ApiResponse> cadastrar(@RequestBody CadrastroRequest request) throws Exception {
    try{
      administradorService.cadastrar(request.getName(), request.getEmail(), request.getPassword(), request.getCpf());
      return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Atualizar senha do administrador")
  @PutMapping("/administrador")
  public ResponseEntity<ApiResponse> atualizarSenha(@RequestBody AtualizarSenhaRequest request){
    try{
      administradorService.atualizarSenha(request);
      return ResponseEntity.ok().body(new ApiResponse("Senha atualizada com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Deletar por ID", description = "utilizado para deletar o administrador, é necessário autenticação para conseguir concluir a ação.")
  @SecurityRequirement(name = "Bearer Authentication")
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
