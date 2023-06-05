package com.tropical.flowers.tropicalflowers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.ProductDTO;
import com.tropical.flowers.tropicalflowers.models.Product;

@Service
public interface ProductService {

  public List<Product> buscarTodos();

  public Product pegarPorId(String id) throws Exception;

  public Product cadastrar(ProductDTO prodRequest) throws Exception;

  public void atualizarPorId(String id, ProductDTO prodRequest) throws Exception;

  public void deletarPorId(String id) throws Exception;
  
}
