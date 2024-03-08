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

     // fin customer by email

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        var customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
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
        return ResponseEntity.ok((CustomerDTO) customerMapper.fromCustomer(createdCustomer));
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
        ResponseEntity<String> isDeleted = customerService.deleteCustomer(id);
        if (isDeleted != null) {
            return ResponseEntity.ok(isDeleted);
        }
        return ResponseEntity.noContent().build();
    }


}
