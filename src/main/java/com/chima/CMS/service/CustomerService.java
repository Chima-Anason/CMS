package com.chima.CMS.service;

import com.chima.CMS.domain.Customer;
import com.chima.CMS.exceptions.CustomerIdException;
import com.chima.CMS.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepositories customerRepositories;


    //Save and Update
    public Customer saveOrUpdateCustomer(Customer customer){
        if (customer.getCustomerId()!= null) {
            customerRepositories.getByCustomerId(customer.getCustomerId());
        }
        return customerRepositories.save(customer);
    }

    //Find Customer by Id
    public Customer findCustomerById(Long id){
        return customerRepositories.getByCustomerId(id);
    }


    //find all customer
    public Iterable<Customer> findAllCustomer(){
        return customerRepositories.findAll();
    }

    //delete a customer
    public void deleteCustomerById(Long customerId){

        Customer customer = customerRepositories.getByCustomerId(customerId);
        if(customer == null){
            throw new CustomerIdException("cannot delete customer with id '"+ customerId+ "'. This customer doesn't exist.");
        }
        customerRepositories.delete(customer);
    }
}
