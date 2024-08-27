package com.wrkspot.services.impl;

import com.wrkspot.models.entities.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class using Specification interface and CriteriaBuilder to handle filter scenarios.
 * @author govil
 */
public class CustomerSpecification {

    private CustomerSpecification() {}

    public static Specification<Customer> byName(String name) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (name == null || name.isEmpty()) {
                return builder.conjunction();
            }
            return builder.or(
                    builder.like(builder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("lastName")), "%" + name.toLowerCase() + "%")
            );
        };
    }

    public static Specification<Customer> byCity(String city) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (city == null || city.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.join("addresses").get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    public static Specification<Customer> byState(String state) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (state == null || state.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.join("addresses").get("state")), "%" + state.toLowerCase() + "%");
        };
    }
}
