package com.tropical.flowers.tropicalflowers.dto;

import lombok.Data;

@Data
public class ResponseCalculoFrete {
  private String ceporigem;
  private String cepdestino;
  private String valorpac;
  private String prazopac;
  private String valorsedex;
  private String prazosedex;

  public ResponseCalculoFrete(){
  }

  public ResponseCalculoFrete(String ceporigem, String cepdestino, String valorpac, String prazopac, String valorsedex,
      String prazosedex) {
    this.ceporigem = ceporigem;
    this.cepdestino = cepdestino;
    this.valorpac = valorpac;
    this.prazopac = prazopac;
    this.valorsedex = valorsedex;
    this.prazosedex = prazosedex;
  }
}