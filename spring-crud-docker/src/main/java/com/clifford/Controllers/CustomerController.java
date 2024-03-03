package com.clifford.Controllers;


import com.clifford.Services.CustomerService;
import com.clifford.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // inject the customer repository
    @Autowired
    private CustomerService customerService;


    // get all customers
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();

    }
   // get customer by id

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

     // Creat post new customer
     @PostMapping
     public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
     }

//    Update customer
     @PutMapping("/{id}")
    public  Customer UpdateCustomer (@PathVariable  Integer id, @RequestBody Customer customer) {
        Customer customerToUpdate = customerService.getCustomerById(id);
        if (customerToUpdate != null) {
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setAge(customer.getAge());
            return customerService.createCustomer(customerToUpdate);
        }
        return null;
     }


     // Delete customer
     @DeleteMapping("/{id}")

    public  void  deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
     }



}
