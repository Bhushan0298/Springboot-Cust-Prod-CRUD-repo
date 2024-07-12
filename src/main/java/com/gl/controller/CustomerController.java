package com.gl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.model.Customer;
import com.gl.model.Product;
import com.gl.repository.CustomerRepository;
import com.gl.repository.ProductRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        Optional<Product> productOptional = productRepository.findById(customer.getProduct().getPid());
        if (productOptional.isPresent()) {
            customer.setProduct(productOptional.get());
            return customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Product ID " + customer.getProduct().getPid() + " does not exist.");
        }
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @GetMapping("/name")
    public List<Customer> getCustomersByName(@RequestParam String cname) {
        return customerRepository.findByCname(cname);
    }

    @GetMapping("/product")
    public List<Customer> getCustomersByProductName(@RequestParam String productName) {
        return customerRepository.findAllByProductName(productName);
    }
}

