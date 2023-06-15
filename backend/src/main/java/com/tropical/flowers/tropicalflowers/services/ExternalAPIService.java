package com.tropical.flowers.tropicalflowers.services;

import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.ResponseCalculoFrete;

@Service
public interface ExternalAPIService {
  public ResponseCalculoFrete calcular(String cep) throws Exception;
}
