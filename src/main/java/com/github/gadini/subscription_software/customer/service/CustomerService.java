package com.github.gadini.subscription_software.customer.service;

import com.github.gadini.subscription_software.customer.dto.PatchCustomerRequestDto;
import com.github.gadini.subscription_software.exception.NotFoundException;
import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.entity.Customer;
import com.github.gadini.subscription_software.customer.enums.CustomerStatusEnum;
import com.github.gadini.subscription_software.customer.mapper.CustomerMapper;
import com.github.gadini.subscription_software.customer.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> findAllCustomers(Pageable pageable){
        return customerRepository.findAll(pageable).map(customerMapper::toResponse);
    }

    @Transactional
    public CustomerResponseDto patchCustomerById(Long id, PatchCustomerRequestDto requestDto) {
        Customer customer = customerRepository.findById(id).orElseThrow( () -> new NotFoundException(Customer.class.getName(), id));
        customerMapper.toPatch(requestDto, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    public void deleteCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow( () -> new NotFoundException(Customer.class.getName(), id));
        customerRepository.delete(customer);
    }
}
