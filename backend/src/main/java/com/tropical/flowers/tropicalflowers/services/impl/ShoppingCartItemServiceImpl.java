package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.ItemRequest;
import com.tropical.flowers.tropicalflowers.models.Product;
import com.tropical.flowers.tropicalflowers.models.ShoppingCart;
import com.tropical.flowers.tropicalflowers.models.ShoppingCartItem;
import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.ProductRepository;
import com.tropical.flowers.tropicalflowers.repositories.ShoppingCartItemRepository;
import com.tropical.flowers.tropicalflowers.repositories.ShoppingCartRepository;
import com.tropical.flowers.tropicalflowers.services.ShoppingCartItemService;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService{

  @Autowired
  private ShoppingCartRepository cartRepository;

  @Autowired
  private ShoppingCartItemRepository itemRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public String criarItem(ItemRequest itemRequest, User user) throws Exception {
    try{
      Optional<ShoppingCart> cart = cartRepository.findByUser(user);
      if(!cart.isPresent()){
        throw new Exception("Carrinho não tem usuário");
      }
      Optional<Product> product = productRepository.findById(itemRequest.getIdProduto());
      ShoppingCartItem item = new ShoppingCartItem();
      item.setCart(cart.get());
      item.setProduct(product.get());
      item.setQuantityItems(itemRequest.getQuantidade());
      ShoppingCartItem itemCriado = itemRepository.save(item);
      if(!itemCriado.getId().isEmpty()){
        return "Criado com sucesso";
      }
      else{
        return "Não foi possivel criar o item";
      }
      
    }
    catch(Exception e){
      throw new Exception("Erro interno no serviço!");
    }
  }

  @Override
  public String deletar(String id) throws Exception {
    try{  
      itemRepository.deleteById(id);
      return "Deletado com sucesso";
    }
    catch(Exception e){
      throw new Exception("Erro interno no serviço!");
    }
  }

  @Override
  public String atualizar(String id, ItemRequest itemRequest) throws Exception {
    try{  
      Optional<ShoppingCartItem> item = itemRepository.findById(id);
      item.get().setQuantityItems(itemRequest.getQuantidade());
      itemRepository.save(item.get());
      return "atualizado com sucesso";
    }
    catch(Exception e){
      throw new Exception("Erro interno no serviço!");
    }
  }
  
}
