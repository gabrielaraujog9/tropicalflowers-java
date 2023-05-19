package com.tropical.flowers.tropicalflowers.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
  /**
  * Gera um hash utilizando o BCrypt.
  *
  * @param senha
  * @return String
  */
  public static String gerarBCrypt(String senha) {
    if(senha == null) return senha;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    return bCryptPasswordEncoder.encode(senha);
   
  }

  /**
  * Verifica se a senha é válida.
  *
  * @param senha
  * @param senhaEncoded
  * @return boolean
  */
  public  static  boolean verificarSenha(String senha, String hash) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    return bCryptPasswordEncoder.matches(senha, hash);
  }

}
