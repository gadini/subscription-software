package com.github.gadini.subscription_software.customer.service;

import com.github.gadini.subscription_software.config.exception.NotFoundException;
import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.entity.Customer;
import com.github.gadini.subscription_software.customer.enums.CustomerStatusEnum;
import com.github.gadini.subscription_software.customer.mapper.CustomerMapper;
import com.github.gadini.subscription_software.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerResponseDto saveCustomer(CustomerRequestDto customerDto){
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setStatusId(CustomerStatusEnum.ACTIVE.getId());
        customer.setStatusName(CustomerStatusEnum.ACTIVE);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow( () -> new NotFoundException(Customer.class.getName(), id));
        return customerMapper.toResponse(customer);
    }
}
