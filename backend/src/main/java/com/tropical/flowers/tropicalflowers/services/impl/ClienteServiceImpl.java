package com.tropical.flowers.tropicalflowers.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tropical.flowers.tropicalflowers.dto.AtualizarSenhaRequest;
import com.tropical.flowers.tropicalflowers.models.Cliente;
import com.tropical.flowers.tropicalflowers.repositories.ClienteRepository;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;
import com.tropical.flowers.tropicalflowers.services.ClienteService;
import com.tropical.flowers.tropicalflowers.services.ShoppingCartService;

@Service
public class ClienteServiceImpl implements ClienteService {
  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private ShoppingCartService shoppingCartService;

  
  @Override
  public void cadastrar(String name, String email, String password, String cpf) throws Exception {
    if(userRepository.findByEmail(email).isPresent()){
      throw new Exception("Usuário já cadastrado!");
    }
    Cliente cliente = new Cliente();
    cliente.setName(name);
    cliente.setEmail(email);
    cliente.setCpf(cpf);
    cliente.setPassword(passwordEncoder.encode(password));
    
    Cliente clienteCriado = clienteRepository.save(cliente);
    shoppingCartService.criar(clienteCriado.getId());
  }


  @Override
  public List<Cliente> buscarTodos() {
    return clienteRepository.findAll();
  }

  @Override
  public Cliente pegarPorId(String id) throws Exception {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    if(!cliente.isPresent()){
      throw new Exception("Não existe usuário com este ID");
    }
    return cliente.get();
  }

  @Override
  public void atualizarSenha(AtualizarSenhaRequest request) throws Exception {
    Optional<Cliente> cliente = clienteRepository.findByEmail(request.getEmail());
    if(!cliente.isPresent()){
      throw new Exception("Usuário não cadastrado!");
    }
    String newPassword = passwordEncoder.encode(request.getPassword());
    
    cliente.get().setPassword(newPassword);

    clienteRepository.save(cliente.get());
  }


  @Override
  public void deletarPorId(String id) {
    try{
      clienteRepository.deleteById(id);
    }catch(Exception e){
      throw e;
    }
  }
}