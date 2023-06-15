package com.tropical.flowers.tropicalflowers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.flowers.tropicalflowers.dto.ResponseCalculoFrete;
import com.tropical.flowers.tropicalflowers.services.ExternalAPIService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ExternalAPIController {
  
  @Autowired
  private ExternalAPIService externalAPIService;

  @GetMapping("/calculo-frete/{cep}")
  public ResponseEntity<Object> calcular(@PathVariable String cep){
    try{
      return ResponseEntity.ok().body(externalAPIService.calcular(cep));
    }catch(Exception e){
      return ResponseEntity.ok().body(e.getMessage());
    }
  }
}
