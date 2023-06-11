package com.tropical.flowers.tropicalflowers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.AtualizarSenhaRequest;
import com.tropical.flowers.tropicalflowers.models.Administrador;

@Service
public interface AdministradorService {

  void cadastrar(String name, String email, String password, String cpf) throws Exception;

  List<Administrador> buscarTodos();

  Administrador pegarPorId(String id) throws Exception;

  void atualizarSenha(AtualizarSenhaRequest request) throws Exception;

  void deletarPorId(String id);
}
