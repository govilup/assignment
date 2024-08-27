package com.wrkspot;

import com.wrkspot.models.entities.Customer;
import com.wrkspot.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceImplTest {

    private CustomerServiceImpl customerService;
    private List<Customer> listA;
    private List<Customer> listB;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl();

        Customer customer1 = new Customer(1L, "John", "Doe", 5000.0, new HashSet<>());
        Customer customer2 = new Customer(2L, "Jane", "Doe", 6000.0, new HashSet<>());
        Customer customer3 = new Customer(3L, "Alice", "Smith", 7000.0, new HashSet<>());

        listA = Arrays.asList(customer1, customer2);
        listB = Arrays.asList(customer2, customer3);
    }

    @Test
    public void testGetCustomersOnlyInA() {
        List<Customer> onlyInA = customerService.getCustomersOnlyInA(listA, listB);
        assertEquals(1, onlyInA.size());
        assertEquals(1L, onlyInA.get(0).getCustomerNumber());
    }

    @Test
    public void testGetCustomersOnlyInB() {
        List<Customer> onlyInB = customerService.getCustomersOnlyInB(listA, listB);
        assertEquals(1, onlyInB.size());
        assertEquals(3L, onlyInB.get(0).getCustomerNumber());
    }

    @Test
    public void testGetCustomersInBothAAndB() {
        List<Customer> inBothAAndB = customerService.getCustomersInBothAAndB(listA, listB);
        assertEquals(1, inBothAAndB.size());
        assertEquals(2L, inBothAAndB.get(0).getCustomerNumber());
    }

}
