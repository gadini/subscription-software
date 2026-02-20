package com.github.gadini.subscription_software.customer.dto;

public record CustomerRequestDto(
        String name,
        String email,
        String document
) { }
