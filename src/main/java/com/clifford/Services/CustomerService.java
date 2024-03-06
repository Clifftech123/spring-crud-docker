package com.clifford.Services;

import com.clifford.Repository.CustomerRepository;
import com.clifford.exception.CustomerNotFoundException;
import com.clifford.interfaces.ICustomerService;
import com.clifford.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Override
    public List<Customer> getAllCustomers() {

        var customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found");
        }
        return new ResponseEntity<>(customers, HttpStatus.OK).getBody();
    }


    // getting customer by id
    @Override
    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
        return new ResponseEntity<>(customer, HttpStatus.OK).getBody();
    }

    // creating new customer

    @Override
  
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    // updating customer
    public ResponseEntity<Customer> updateCustomer(Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElse(null);
        if (customerToUpdate != null) {
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAge(customer.getAge());
            Customer updatedCustomer = customerRepository.save(customerToUpdate);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    // deleting customer
    @Override
    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
