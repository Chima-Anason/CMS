package com.chima.CMS.controller;

import com.chima.CMS.domain.Customer;
import com.chima.CMS.exceptions.CustomerIdException;
import com.chima.CMS.service.CustomerService;
import com.chima.CMS.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewCustomer(@Valid  @RequestBody Customer customer, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;

        Customer customer1 = customerService.saveOrUpdateCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/id/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId){

        Customer customer = customerService.findCustomerById(customerId);
        if(customer == null){
            throw new CustomerIdException("Customer Id '"+ customerId + "' doesn't exist");
        }
        return customer;
    }

    @GetMapping("/all")
    public Iterable<Customer> getAllCustomer(){
        return customerService.findAllCustomer();
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable  Long customerId){

        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<String>("Customer with ID: '"+customerId+"' was deleted", HttpStatus.OK);
    }



}


