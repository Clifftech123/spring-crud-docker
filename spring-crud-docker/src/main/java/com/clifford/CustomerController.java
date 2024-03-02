package com.clifford;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // inject the customer repository
    @Autowired
    private CustomerRespository customerRepository;


    // get all customers
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();

    }
    // Get customer by it

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

     // Creat post new customer
     @PostMapping
     public Customer createCustomer(@RequestBody Customer customer) {
         return customerRepository.save(customer);
     }


     @PutMapping("/{id}")
    public  Customer UpdateCustomer (@PathVariable  Integer id, @RequestBody Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElse(null);
        if (customerToUpdate != null) {
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAge(customer.getAge());
            return customerRepository.save(customerToUpdate);
        }
        return null;
     }

     @DeleteMapping("/{id}")

    public  void  deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
     }



}
