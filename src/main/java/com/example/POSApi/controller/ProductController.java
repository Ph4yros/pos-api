package com.example.POSApi.controller;

import com.example.POSApi.model.Product;
import com.example.POSApi.respository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // Tüm ürünler (user_id parametre ile)
    @GetMapping("/{userId}")
    public List<Product> getAllProducts(@PathVariable int userId) {
        return repo.findByUserId(userId);
    }

    // Barkod ile ürün bul
    @GetMapping("/barcode/{userId}/{barcode}")
    public Product getByBarcode(@PathVariable int userId, @PathVariable String barcode) {
        return repo.findByUserId(userId).stream()
                .filter(p -> p.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }
}
