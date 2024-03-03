package com.clifford.interfaces;

import com.clifford.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer id);
    Customer createCustomer(Customer customer);
    void deleteCustomer(Integer id);

}
