package com.example.POSApi.respository;

import com.example.POSApi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByUserId(Long userId); // userId’ye göre satışları bul
}
