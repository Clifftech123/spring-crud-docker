package com.clifford.Mapper;

import com.clifford.DTO.CustomerDTO;
import com.clifford.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-03T16:40:08+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ICustomerMapperImpl implements ICustomerMapper {

    @Override
    public CustomerDTO toCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setEmail( customer.getEmail() );
        customerDTO.setAge( customer.getAge() );

        return customerDTO;
    }

    @Override
    public Customer fromCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setEmail( customerDTO.getEmail() );
        customer.setAge( customerDTO.getAge() );

        return customer;
    }
}
