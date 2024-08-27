package com.wrkspot.controllers;

import com.wrkspot.models.dtos.CustomerDto;
import com.wrkspot.models.entities.Customer;
import com.wrkspot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto response = customerService.createCustomer(customerDto);
        if (response == null) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {

        List<Customer> customers = customerService.findCustomer(name, city, state);
        return ResponseEntity.ok(customers);
    }
}
