package com.github.gadini.subscription_software.customer.assembler;

import com.github.gadini.subscription_software.customer.controller.CustomerController;
import com.github.gadini.subscription_software.customer.dto.CustomerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerAssembler implements RepresentationModelAssembler<CustomerResponseDto, EntityModel<CustomerResponseDto>> {

    private final PagedResourcesAssembler<CustomerResponseDto> pagedResourcesAssembler;

    public CustomerAssembler(PagedResourcesAssembler<CustomerResponseDto> pagedResourcesAssembler) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    @NonNull
    public EntityModel<CustomerResponseDto> toModel(@NonNull CustomerResponseDto dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(CustomerController.class).findById(dto.id())).withSelfRel().withType("GET"),
                linkTo(methodOn(CustomerController.class).findAll(Pageable.unpaged())).withRel("findAll").withType("GET"),
                linkTo(methodOn(CustomerController.class).create(null)).withRel("create").withType("POST"),
                linkTo(methodOn(CustomerController.class).patch(dto.id(), null)).withRel("patch").withType("PATCH"),
                linkTo(methodOn(CustomerController.class).delete(dto.id())).withRel("delete").withType("DELETE")
        );
    }

    public PagedModel<EntityModel<CustomerResponseDto>> toPagedModel(Page<CustomerResponseDto> page) {
        return pagedResourcesAssembler.toModel(page, this);
    }
}