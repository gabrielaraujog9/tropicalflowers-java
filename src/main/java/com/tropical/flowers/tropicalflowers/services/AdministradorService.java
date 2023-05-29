package com.tropical.flowers.tropicalflowers.services;

public interface AdministradorService {
  void cadastrar(String name, String email, String password, String cpf) throws Exception;
}
