package com.github.gadini.subscription_software.customer.controller;

import com.github.gadini.subscription_software.customer.assembler.CustomerAssembler;
import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.dto.PatchCustomerRequestDto;
import com.github.gadini.subscription_software.customer.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    private final CustomerAssembler customerAssembler;

    private final CustomerService customerService;

    public CustomerController(CustomerAssembler customerAssembler, CustomerService customerService) {
        this.customerAssembler = customerAssembler;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<CustomerResponseDto>> create(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto responseDto = customerService.saveCustomer(customerRequestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(responseDto.id()).toUri();

        return ResponseEntity.created(uri).body(customerAssembler.toModel(responseDto));
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<EntityModel<CustomerResponseDto>> findById(@PathVariable Long customerId){
        return ResponseEntity.ok(customerAssembler.toModel(customerService.getCustomerById(customerId)));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CustomerResponseDto>>> findAll(@PageableDefault(size = 30) Pageable pageable){
        Page<CustomerResponseDto> page = customerService.findAllCustomers(pageable);
        return ResponseEntity.ok(customerAssembler.toPagedModel(page));
    }

    @PatchMapping(value = "/{customerId}")
    public ResponseEntity<EntityModel<CustomerResponseDto>> patch(@PathVariable Long customerId, @RequestBody PatchCustomerRequestDto requestDto){
        return ResponseEntity.ok(customerAssembler.toModel(customerService.patchCustomerById(customerId, requestDto)));
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

}
