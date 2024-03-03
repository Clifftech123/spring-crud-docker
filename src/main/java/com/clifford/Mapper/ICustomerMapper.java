package com.clifford.Mapper;

import com.clifford.DTO.CustomerDTO;
import com.clifford.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);
    Customer fromCustomer(CustomerDTO customerDTO);
}
