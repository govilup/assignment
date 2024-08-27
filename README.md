## Assignment

### Requirement 1

Implement an API to save customers into database. Customer data model looks like
```json
[{
"customer": {
"firstName": "",
"lastName": "",
"customerId": "",
"age": 30,
"spendingLimit": 450000.001244,
"mobileNumber": "",
"address": [{
    "type": "",
    "address1": "",
    "address2": "",
    "city": "",
    "state": "",
    "zipCode": ""
    }]
  }
}]
```

Publish the message to Kafka on successful save.

### Requirement 2

Implement an API to fetch the customers by name, city and state. If no filter is provided return all customers.

### Requirement 3

You are provided with two lists of Customers

A and B as below

List<Customer> A , List<Customer> B

Please code the for the following questions

1) Customers only in list A
2) Customers only List B
3) Customers in both A and B

## Solutions

Technology used to develop this service
* Java 17
* Spring Boot V3.3.3
* REST APIs
* Maven
* Lombok
* Spring Cloud Stream
* PostgreSql

Command to build the service
```java
mvn clean install
```

Command to run the service

```java
mvn spring-boot:run
```

#### Implementation

Application properties are under application.yml file. Server boots up at 8081.

There are two APIs in the application present under CustomerController.

Using spring data jpa repository for performing DB operation. To perform filter operation using **Specification** and **Criteria Builder**. There is also usage of 
spring cloud stream to configure Kafka publisher. All the kafka related props went into the application.yml. This way we can easily switch to a different message queue in future.

**Requirement 3** functions are written in CustomerServiceImpl and can be tested from the test case. 

#### Improvements

* Logging
* Testing

