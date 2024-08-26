package com.wrkspot.services;

import com.wrkspot.models.dtos.CustomerDto;
import com.wrkspot.models.entities.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto request);

    List<Customer> findCustomer(String name, String city, String state);
}
