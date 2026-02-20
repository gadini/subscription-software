package com.github.gadini.subscription_software.customer.entity;

import com.github.gadini.subscription_software.customer.enums.CustomerStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "document", length = 20, nullable = false)
    private String document;

    @Column(name = "status_id", nullable = false)
    private int statusId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_name", length = 20,nullable = false)
    private CustomerStatusEnum statusName;

    public Customer() { }

    public Customer(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String email, String document, int statusId, CustomerStatusEnum statusName) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.email = email;
        this.document = document;
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public CustomerStatusEnum getStatusName() {
        return statusName;
    }

    public void setStatusName(CustomerStatusEnum statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return statusId == customer.statusId && Objects.equals(id, customer.id) && Objects.equals(createdAt, customer.createdAt) && Objects.equals(updatedAt, customer.updatedAt) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(document, customer.document) && Objects.equals(statusName, customer.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, name, email, document, statusId, statusName);
    }
}
