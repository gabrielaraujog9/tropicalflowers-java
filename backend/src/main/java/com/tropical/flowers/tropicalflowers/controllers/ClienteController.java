package com.tropical.flowers.tropicalflowers.controllers;

import com.tropical.flowers.tropicalflowers.dto.ApiResponse;
import com.tropical.flowers.tropicalflowers.dto.AtualizarSenhaRequest;
import com.tropical.flowers.tropicalflowers.dto.CadrastroRequest;
import com.tropical.flowers.tropicalflowers.models.Cliente;
import com.tropical.flowers.tropicalflowers.services.ClienteService;

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
@Tag(name = "Cliente")
public class ClienteController {
  @Autowired
  private ClienteService clienteService;

  @Operation(summary = "Buscar por ID", description = "utilizado para buscar o cliente, é necessário autenticação para conseguir concluir a ação.")
  @SecurityRequirement(name = "Bearer Authentication")
  @GetMapping("/cliente/{id}")
  public ResponseEntity<Cliente> pegarPorId(@PathVariable("id") String id) throws Exception{
    return ResponseEntity.ok().body(clienteService.pegarPorId(id));
  }

  @Operation(summary = "Cadastrar", description = "utilizado para cadastrar o cliente")
  @PostMapping("/cliente")
  public ResponseEntity<ApiResponse> cadastrar(@RequestBody CadrastroRequest request) throws Exception   {
    try{
      clienteService.cadastrar(request.getName(), request.getEmail(), request.getPassword(), request.getCpf());
      return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  } 
 
  @Operation(summary = "Alterar senha")
  @PutMapping("/cliente")
  public ResponseEntity<ApiResponse> atualizarSenha(@RequestBody AtualizarSenhaRequest request){
    try{
      clienteService.atualizarSenha(request);
      return ResponseEntity.ok().body(new ApiResponse("Senha atualizada com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }

  @Operation(summary = "Deletar por ID")
  @SecurityRequirement(name = "Bearer Authentication")
  @DeleteMapping("/cliente/{id}")
  public ResponseEntity<ApiResponse> deletarPorId(@PathVariable("id") String id) throws Exception{
    try{
      clienteService.deletarPorId(id);
      return ResponseEntity.ok().body(new ApiResponse("Usuário deletado com sucesso!"));
    }catch(Exception e){
      return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
    }
  }
}
