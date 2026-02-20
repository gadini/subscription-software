package com.github.gadini.subscription_software.customer.enums;

import java.util.Arrays;

public enum CustomerStatusEnum {

    ACTIVE(1, "Ativo");

    private final Integer id;
    private final String status;

    CustomerStatusEnum(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public static CustomerStatusEnum fromId(Integer id) {
        return Arrays.stream(values())
                .filter(e -> e.id.equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Status não encontrado para id=" + id));
    }

    public static CustomerStatusEnum fromStatus(String status) {
        return Arrays.stream(values())
                .filter(e -> e.status.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Status não encontrado para status=" + status));
    }
}
