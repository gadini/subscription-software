package com.github.gadini.subscription_software.customer.dto;

public record PatchCustomerRequestDto (
        String name,
        String email,
        String document,
        String statusName
) { }
