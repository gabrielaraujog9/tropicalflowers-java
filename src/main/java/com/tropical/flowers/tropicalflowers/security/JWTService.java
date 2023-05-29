package com.tropical.flowers.tropicalflowers.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {
  
  @Value("${SECRET_KEY}")
  private String chavePrivadaJWT;

  public String gerarToken(Authentication authentication) {
    try{

      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
  
      Date dataAtual = new Date();
      int tempoExpiracao = 86400000; // 1 dia em milisegundos
      Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
      // String token = Jwts.builder()
      // .setSubject(userPrincipal.getId())
      // .setIssuedAt(dataAtual)
      // .setExpiration(dataExpiracao)
      // .signWith(SignatureAlgorithm.HS256, chavePrivadaJWT)
      // .compact();
      Algorithm algorithm = Algorithm.HMAC256(chavePrivadaJWT);
      String token = JWT.create()
                      .withSubject(userPrincipal.getId())
                      .withIssuedAt(dataAtual)
                      .withExpiresAt(dataExpiracao)
                      .sign(algorithm);
      return token;
    }catch(Exception e){
      System.out.println("#########" + e.getMessage() + "#########");
      return e.getMessage();
    }

  }
  public Optional<String> getUserId(String jwt) {
    try {
      DecodedJWT decodedJWT = parse(jwt);
      return Optional.ofNullable(decodedJWT.getSubject());
    } catch (Exception ex) {
      return Optional.empty();
    }
  }
  private DecodedJWT parse(String jwt) {
    Algorithm algorithm = Algorithm.HMAC256(chavePrivadaJWT);
    return JWT.require(algorithm).build().verify(jwt);
  }
}
