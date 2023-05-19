package com.tropical.flowers.tropicalflowers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tropical.flowers.tropicalflowers.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
