package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.ProductDTO;
import com.tropical.flowers.tropicalflowers.models.Product;
import com.tropical.flowers.tropicalflowers.repositories.ProductRepository;
import com.tropical.flowers.tropicalflowers.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  ProductRepository productRepository;

  @Override
  public List<Product> buscarTodos() {
    return productRepository.findAll();
  }

  @Override
  public Product pegarPorId(String id) throws Exception {
    Optional<Product> product = productRepository.findById(id);
    if(!product.isPresent()){
      throw new Exception("Não existe produto com este ID");
    }
    return product.get();
  }

  @Override
  public Product cadastrar(ProductDTO prodDTO) throws Exception {
    try{
      Product prodRequest = new Product();
      prodRequest.setName(prodDTO.getName());
      prodRequest.setDescription(prodDTO.getDescription());
      prodRequest.setPrice(prodDTO.getPrice());
      prodRequest.setStock(prodDTO.getStock());
      prodRequest.setImage(prodDTO.getImage());
  
      Product product = productRepository.save(prodRequest);
  
      return product;
    }catch(Exception e){
      throw new Exception(e);
    }
  }

  @Override
  public void atualizarPorId(String id, ProductDTO prodRequest) throws Exception {
    try{
      Product product = productRepository.findById(id).get();
      if(prodRequest.getName() != product.getName()){
        product.setName(prodRequest.getName());
      }
      if(prodRequest.getDescription() != product.getDescription()){
        product.setDescription(prodRequest.getDescription());
      }
      if(prodRequest.getImage() != product.getImage()){
        product.setImage(prodRequest.getImage());
      }
      if(prodRequest.getPrice() != product.getPrice()){
        product.setPrice(prodRequest.getPrice());
      }
      if(prodRequest.getStock() != product.getStock()){
        product.setStock(prodRequest.getStock());
      }
      productRepository.save(product);
    }catch(Exception e){
      throw new Exception("Erro ao atualizar o produto!");
    }
  }

  @Override
  public void deletarPorId(String id) throws Exception {
    try{
      productRepository.deleteById(id);
    }catch(Exception e){
      throw new Exception("Erro ao deletar o produto");
    }
  }
  
}
