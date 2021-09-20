package com.codegym.service.customer.impl;

import com.codegym.model.Customer;
import com.codegym.repository.customer.ICustomerRepository;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository ;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remote(Long id) {
        customerRepository.remote(id);
    }
}
