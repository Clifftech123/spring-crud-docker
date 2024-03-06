package com.clifford.Controllers;


import com.clifford.DTO.CustomerDTO;
import com.clifford.Mapper.ICustomerMapper;
import com.clifford.Services.CustomerService;
import com.clifford.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    // inject the customer repository
    @Autowired
    private CustomerService customerService;
    private ICustomerMapper customerMapper;


    // get all customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());

    }
    // get customer by id

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer id) {
        var customer = customerService.getCustomerById(id);

         if (customer  != null) {
            var foundCustomer = customerMapper.fromCustomer(customer);
            return ResponseEntity.ok(foundCustomer);
         }
         return ResponseEntity.notFound().build();



    }

    // Creat post new customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        var creatCustomer = customerMapper.toCustomer(customerDTO);
        var createdCustomer = customerService.createCustomer(creatCustomer);
        var customer = customerMapper.fromCustomer(createdCustomer);
        return ResponseEntity.ok(customer);
    }

    //    Update customer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @Validated @RequestBody CustomerDTO customerDTO) {
        Customer customerToUpdate = customerService.getCustomerById(id);
        if (customerToUpdate != null) {
            customerToUpdate.setName(customerDTO.getName());
            customerToUpdate.setEmail(customerDTO.getEmail());
            customerToUpdate.setAge(customerDTO.getAge());
            Customer updatedCustomer = customerService.updateCustomer(customerToUpdate).getBody();
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }


    // Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        boolean isDeleted = customerService.deleteCustomer(id);
        if (isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}
