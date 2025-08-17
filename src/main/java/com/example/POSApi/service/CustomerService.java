package com.example.POSApi.service;

import com.example.POSApi.model.Customer;
import com.example.POSApi.respository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(Customer customer, Long userId) {
        Optional<Customer> existing = repository.findByIdAndUserId(customer.getId(), userId);
        if (existing.isPresent()) {
            customer.setUserId(userId); // emin ol
            return repository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(Long customerId, Long userId) {
        Optional<Customer> existing = repository.findByIdAndUserId(customerId, userId);
        if (existing.isPresent()) {
            repository.delete(existing.get());
            return true;
        }
        return false;
    }

    public List<Customer> getAllCustomers(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<Customer> searchCustomers(String keyword, Long userId) {
        return repository.findByNameContainingIgnoreCaseAndUserId(keyword, userId);
    }

    public Customer findByPhone(String phone, Long userId) {
        return repository.findByPhoneAndUserId(phone, userId).orElse(null);
    }

    public Customer findById(Long id, Long userId) {
        return repository.findByIdAndUserId(id, userId).orElse(null);
    }
}
