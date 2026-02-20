package com.github.gadini.subscription_software.customer.mapper;

import com.github.gadini.subscription_software.customer.dto.CustomerRequestDto;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import com.github.gadini.subscription_software.customer.dto.PatchCustomerRequestDto;
import com.github.gadini.subscription_software.customer.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toResponse(Customer customer);

    Customer toEntity(CustomerRequestDto customerRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toPatch(PatchCustomerRequestDto patchCustomerRequestDto, @MappingTarget Customer customer);

}
