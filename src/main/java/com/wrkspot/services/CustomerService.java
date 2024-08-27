package com.wrkspot.services;

import com.wrkspot.models.dtos.CustomerDto;
import com.wrkspot.models.entities.Customer;

import java.util.List;

/**
 * Service interface to manage functions related to customer.
 *
 * @author govil
 */
public interface CustomerService {

    /**
     * Create customer
     *
     * @param request
     * @return customer dto on successful save operation
     */
    CustomerDto createCustomer(CustomerDto request);

    /**
     * Search for a customer based on specific filter.
     *
     * @param name
     * @param city
     * @param state
     * @return Customer or List<Customer> return type is kept as List.
     */
    List<Customer> findCustomer(String name, String city, String state);
}
