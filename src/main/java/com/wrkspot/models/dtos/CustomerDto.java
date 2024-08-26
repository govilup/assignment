package com.wrkspot.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@Builder(toBuilder = true)
public class CustomerDto {

    @NotBlank(message = "Please enter first name")
    private String firstName;
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotBlank(message = "Spending limit cannot be empty")
    private Double spendingLimit;
    private Set<AddressDto> addresses;
}
