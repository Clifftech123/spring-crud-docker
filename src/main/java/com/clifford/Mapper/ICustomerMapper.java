package com.clifford.Mapper;

import com.clifford.DTO.CustomerDTO;
import com.clifford.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    Customer toCustomer(CustomerDTO customerDTO);
CustomerDTO fromCustomer(Customer customer);

}
