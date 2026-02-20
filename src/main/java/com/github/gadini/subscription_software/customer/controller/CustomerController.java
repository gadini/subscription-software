package com.github.gadini.subscription_software.customer.controller;

import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.dto.PatchCustomerRequestDto;
import com.github.gadini.subscription_software.customer.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
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

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> listAllCustormers(@PageableDefault(size = 30) Pageable pageable){
        return ResponseEntity.ok(customerService.findAllCustomers(pageable));
    }

    @PatchMapping(value = "/{customerId}")
    public ResponseEntity<CustomerResponseDto> patchCustomerById(@PathVariable Long customerId, @RequestBody PatchCustomerRequestDto requestDto){
        return ResponseEntity.ok(customerService.patchCustomerById(customerId, requestDto));
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

}
