package com.gl.repository;

import com.gl.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 1. Select All Customers with a Specific Product
    @Query("SELECT c FROM Customer c WHERE c.product.pname = :productName")
    List<Customer> findAllByProductName(String productName);

    // 2. Find Customers by Name
    List<Customer> findByCname(String cname);
}
