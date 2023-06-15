package com.tropical.flowers.tropicalflowers.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tropical.flowers.tropicalflowers.dto.ResponseCalculoFrete;
import com.tropical.flowers.tropicalflowers.services.ExternalAPIService;

@Service
public class ExternalAPIServiceImpl implements ExternalAPIService {

  @Autowired
  private  WebClient webClient;

  @Override
  public ResponseCalculoFrete calcular(String cep) throws Exception {
    String url = "https://www.cepcerto.com/ws/json-frete/52211250/" + cep + "/500";

    return webClient.get()
                 .uri(url)
                 .retrieve()
                 .bodyToMono(ResponseCalculoFrete.class)
                 .block();
   
  }
}
