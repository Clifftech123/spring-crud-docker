package com.clifford.Services;

import com.clifford.Repository.CustomerRepository;
import com.clifford.interfaces.ICustomerService;
import com.clifford.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    // inject the customer repository
    private final CustomerRepository customerRepository;
    private Integer id;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // getting all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    // getting customer by id
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    // creating new customer


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // updating customer
    public Customer updateCustomer(Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).
                orElse(null);
        if (customerToUpdate != null) {
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAge(customer.getAge());
            return customerRepository.save(customerToUpdate);
        }
        return null;
    }


    // deleting customer

    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
