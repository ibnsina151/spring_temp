package com.example.demo.repository;

import com.example.demo.repository.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{

    List<Customer> findByFirstName(String FirstName);
    List<Customer> findAll();

}
