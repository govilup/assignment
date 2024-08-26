package com.wrkspot.mappers;

import com.wrkspot.models.dtos.AddressDto;
import com.wrkspot.models.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}
