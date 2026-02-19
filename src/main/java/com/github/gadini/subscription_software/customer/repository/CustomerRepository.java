package com.github.gadini.subscription_software.customer.repository;

import com.github.gadini.subscription_software.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
