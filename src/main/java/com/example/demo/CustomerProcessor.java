package com.example.demo;

import com.example.demo.model.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


/**
 * Created by gsati on 1/20/2018.
 */
@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    //Customer customer = new Customer();
    @Override
    public Customer process(Customer customer) throws Exception {
       // Customer customer = new Customer();
        String fullName = customer.getCustName().toUpperCase();
        String email = customer.getEmail().toUpperCase();
        //Date date = customer.getDob();
        Customer cust1 = new Customer(customer.getCustId(),fullName,email,customer.getDob());
        return cust1;

    }

}
