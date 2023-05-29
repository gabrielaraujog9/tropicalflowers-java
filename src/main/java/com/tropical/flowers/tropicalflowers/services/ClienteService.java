package com.tropical.flowers.tropicalflowers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.Cliente;

@Service
public interface ClienteService {
  void cadastrar(String name, String email, String password, String cpf) throws Exception;

  List<Cliente> buscarTodos();

  String login(String email, String password) throws Exception;
}
