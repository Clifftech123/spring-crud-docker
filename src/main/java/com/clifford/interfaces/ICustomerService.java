package com.clifford.interfaces;

import com.clifford.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer id);
    Customer createCustomer(Customer customer);
    ResponseEntity<String> deleteCustomer(Integer id);

}
