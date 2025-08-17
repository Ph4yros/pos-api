package com.example.POSApi.controller;

import com.example.POSApi.model.Customer;
import com.example.POSApi.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // TEST: sabit kullanıcı ID, normalde login sonrası JWT ile gelir
    private final Long currentUserId = 1L;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers(currentUserId);
    }

    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String keyword) {
        return service.searchCustomers(keyword, currentUserId);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return service.findById(id, currentUserId);
    }

    @GetMapping("/phone/{phone}")
    public Customer getByPhone(@PathVariable String phone) {
        return service.findByPhone(phone, currentUserId);
    }

    // Yeni: userId ile müşteri listeleme
    @GetMapping("/user/{userId}")
    public List<Customer> getCustomersByUserId(@PathVariable Long userId) {
        return service.getAllCustomers(userId);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setUserId(currentUserId);
        return service.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return service.updateCustomer(customer, currentUserId);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable Long id) {
        return service.deleteCustomer(id, currentUserId);
    }
}
