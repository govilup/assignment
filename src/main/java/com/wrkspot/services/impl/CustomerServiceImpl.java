package com.wrkspot.services.impl;

import com.wrkspot.mappers.CustomerMapper;
import com.wrkspot.models.dtos.CustomerDto;
import com.wrkspot.models.entities.Customer;
import com.wrkspot.repositories.CustomerRepository;
import com.wrkspot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer createdCustomer = customerRepository.save(customerMapper.toEntity(customerDto));
        return customerMapper.toDto(createdCustomer);
    }

    @Override
    public List<Customer> findCustomer(String name, String city, String state) {
        if (isAllParametersEmpty(name, city, state)) {
            return customerRepository.findAll();
        }
        Specification<Customer> spec = Specification
                .where(CustomerSpecification.byName(name))
                .and(CustomerSpecification.byCity(city))
                .and(CustomerSpecification.byState(state));
       return customerRepository.findAll(spec);
    }

    private boolean isAllParametersEmpty(String name, String city, String state) {
        return (name == null && name.isEmpty()) ||
                (city == null && city.isEmpty())||
                (state == null && state.isEmpty());
    }
}
