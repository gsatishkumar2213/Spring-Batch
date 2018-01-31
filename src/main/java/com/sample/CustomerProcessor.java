package com.sample;

import com.sample.model.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by gsati on 1/31/2018.
 */
@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {

        String fullName = customer.getCustName().toUpperCase();
        String email = customer.getEmail().toUpperCase();
        Customer cust1 = new Customer(customer.getCustId(),fullName,email,customer.getDob());
        return cust1;

    }

}