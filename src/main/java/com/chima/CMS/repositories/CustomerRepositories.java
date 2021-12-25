package com.chima.CMS.repositories;

import com.chima.CMS.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepositories extends CrudRepository<Customer , Long> {
//    @Override
//    Iterable<Customer> findAllById(Iterable<long> longs);

    Customer getByCustomerId(Long id);
}
