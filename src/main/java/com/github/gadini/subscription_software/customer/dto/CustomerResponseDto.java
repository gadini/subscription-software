package com.github.gadini.subscription_software.customer.dto;

public record CustomerResponseDto(
        Long id,
        String name,
        String email,
        String document,
        int statusId,
        String statusName
) { }
