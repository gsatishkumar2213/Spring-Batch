package com.example.demo;

import com.example.demo.model.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created by gsati on 1/20/2018.
 */
public class CustomerSetMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        Customer customer = new Customer();
      //  customer.setCustId(Integer.parseInt(fieldSet.readRawString("custId")));
        customer.setCustName(fieldSet.readString("custName"));
        customer.setEmail(fieldSet.readString("email"));
        customer.setDob(fieldSet.readDate("dob"));
        return customer;
    }
}
