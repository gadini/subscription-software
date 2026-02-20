package com.github.gadini.subscription_software.customer.mapper;

import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toResponse(Customer customer);

    Customer toEntity(CustomerRequestDto customerRequestDto);

}
