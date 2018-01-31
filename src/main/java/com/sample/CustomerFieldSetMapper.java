package com.sample;

import com.sample.model.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * Created by gsati on 1/31/2018.
 */
public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        Customer customer = new Customer();
        customer.setCustName(fieldSet.readString("custName"));
        customer.setEmail(fieldSet.readString("email"));
        customer.setDob(fieldSet.readDate("dob"));
        return customer;
    }
}
