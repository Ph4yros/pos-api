package com.example.POSApi.respository;

import com.example.POSApi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // ID ve userId ile bul
    Optional<Customer> findByIdAndUserId(Long id, Long userId);

    // Telefon ve userId ile bul
    Optional<Customer> findByPhoneAndUserId(String phone, Long userId);

    // İsim içeriyorsa ve userId eşleşiyorsa bul
    List<Customer> findByNameContainingIgnoreCaseAndUserId(String name, Long userId);

    // Kullanıcıya ait tüm müşteriler
    List<Customer> findByUserId(Long userId);
}
