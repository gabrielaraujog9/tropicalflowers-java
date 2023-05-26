package com.tropical.flowers.tropicalflowers.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tropical.flowers.tropicalflowers.models.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class UserPrincipal implements UserDetails{

  
  private String id;
  private String name;
  private String email;
  private Date createdAt;
  private Date updatedAt;
  @JsonIgnore
  private String password;
  private String cpf;
  private Collection<? extends GrantedAuthority> authorities;
  
 

  public UserPrincipal(String id, String name, String email, String password, String cpf,
      Collection<? extends GrantedAuthority> authorities, Date createdAt, Date updatedAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.cpf = cpf;
    this.authorities = authorities;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static UserPrincipal create(User usuario) {

    List<GrantedAuthority> authorities = Arrays.asList( new SimpleGrantedAuthority(usuario.getPerfil().getRole()));

    return new UserPrincipal(
            usuario.getId(),
            usuario.getName(),
            usuario.getEmail(),
            usuario.getPassword(),
            usuario.getCpf(),
            authorities,
            usuario.getCreatedAt(),
            usuario.getUpdatedAt()
    );
}

  @Override
  public String getUsername() {
      return email;
  }
  @Override
  @JsonIgnore
  public String getPassword() {
      return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
  
}
