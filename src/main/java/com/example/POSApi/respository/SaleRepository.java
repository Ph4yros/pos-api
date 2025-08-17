package com.example.POSApi.respository;

import com.example.POSApi.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByUserId(Long userId); // userId’ye göre satışları bul
}
