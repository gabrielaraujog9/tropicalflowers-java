package com.tropical.flowers.tropicalflowers.controllers;

import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public ResponseEntity<User> create2() {
    User userCreate = new User("Gabriel", "gabriel@gabriel.com", "1231231", "2131231");
    
    return new ResponseEntity<User>(userService.createUser(userCreate), HttpStatus.OK);
  }
  
  
  @PostMapping("/register")
  public ResponseEntity<User> create(@RequestBody User userCreate) {

    return new ResponseEntity<User>(userService.createUser(userCreate), HttpStatus.OK);
  }

}
