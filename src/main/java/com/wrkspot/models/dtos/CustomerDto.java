package com.wrkspot.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
public class CustomerDto {

    @NotBlank(message = "Please enter first name")
    private String firstName;
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotBlank(message = "Spending limit cannot be empty")
    private Double spendingLimit;
    private Set<AddressDto> addresses;
}
