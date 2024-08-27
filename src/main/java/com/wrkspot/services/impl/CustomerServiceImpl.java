package com.wrkspot.services.impl;

import com.wrkspot.event.Producer;
import com.wrkspot.mappers.CustomerMapper;
import com.wrkspot.models.dtos.CustomerDto;
import com.wrkspot.models.entities.Customer;
import com.wrkspot.repositories.CustomerRepository;
import com.wrkspot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private Producer<Customer> eventProducer;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer createdCustomer = customerRepository.save(customerMapper.toEntity(customerDto));
        eventProducer.sendMessage(createdCustomer);
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

    public List<Customer> getCustomersOnlyInA(List<Customer> A, List<Customer> B) {
        Set<Long> customerNumbersInB = B.stream()
                .map(Customer::getCustomerNumber)
                .collect(Collectors.toSet());

        return A.stream()
                .filter(customer -> !customerNumbersInB.contains(customer.getCustomerNumber()))
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersOnlyInB(List<Customer> A, List<Customer> B) {
        Set<Long> customerNumbersInA = A.stream()
                .map(Customer::getCustomerNumber)
                .collect(Collectors.toSet());

        return B.stream()
                .filter(customer -> !customerNumbersInA.contains(customer.getCustomerNumber()))
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersInBothAAndB(List<Customer> A, List<Customer> B) {
        Set<Long> customerNumbersInA = A.stream()
                .map(Customer::getCustomerNumber)
                .collect(Collectors.toSet());

        return B.stream()
                .filter(customer -> customerNumbersInA.contains(customer.getCustomerNumber()))
                .collect(Collectors.toList());
    }
}
