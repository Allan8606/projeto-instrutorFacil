package com.allan_dev.instrutorFacil.dto;


import lombok.Builder;

@Builder
public record JWTUserData(
        Long id,
        String name,
        String email,
        String role
) {}
