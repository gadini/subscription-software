package com.github.gadini.subscription_software.customer.controller;

import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/vi/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto responseDto = customerService.saveCustomer(customerRequestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(responseDto.id()).toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long customerId){
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

}
