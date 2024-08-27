package com.wrkspot.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AddressDto {

    private String addressType;
    private String street;
    private String city;
    private String state;
    private String zip;
}
