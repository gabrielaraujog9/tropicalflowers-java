package com.tropical.flowers.tropicalflowers.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Perfil {

    ADMINISTRADOR("ROLE_ADMINISTRADOR"),
    CLIENTE("ROLE_CLIENTE");

    private final String role;

}