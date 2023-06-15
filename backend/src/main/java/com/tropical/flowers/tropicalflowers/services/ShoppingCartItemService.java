package com.tropical.flowers.tropicalflowers.services;

import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.ItemRequest;
import com.tropical.flowers.tropicalflowers.models.User;

@Service
public interface ShoppingCartItemService {
  public String criarItem(ItemRequest item, User user) throws Exception;

  public String deletar(String id) throws Exception;

  public String atualizar(String id, ItemRequest item) throws Exception;
}
