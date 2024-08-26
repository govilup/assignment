package com.wrkspot.mappers;

import com.wrkspot.models.dtos.CustomerDto;
import com.wrkspot.models.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

    @Mapping(source = "addresses", target = "addresses")
    CustomerDto toDto(Customer customer);

    @Mapping(source = "addresses", target = "addresses")
    Customer toEntity(CustomerDto customerDto);
}
