package com.tropical.flowers.tropicalflowers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.models.User;

@Service
public interface UserService {

  String login(String email, String password) throws Exception;

  User pegarPorToken(String token) throws Exception;

  List<User> buscarTodos();
}
