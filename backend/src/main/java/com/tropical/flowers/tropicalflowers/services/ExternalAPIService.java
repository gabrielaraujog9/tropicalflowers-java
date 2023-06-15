package com.tropical.flowers.tropicalflowers.services;

import org.springframework.stereotype.Service;

@Service
public interface ExternalAPIService {
  public String calcular(String cep) throws Exception;
}
