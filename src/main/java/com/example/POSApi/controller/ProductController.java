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

    // -----------------------
    // Yeni ürün ekle
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    // Ürün güncelle
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return repo.findById((long) id)
                .map(existing -> {
                    existing.setName(product.getName());
                    existing.setBarcode(product.getBarcode());
                    existing.setPrice(product.getPrice());
                    existing.setStock(product.getStock());
                    existing.setPurchasePrice(product.getPurchasePrice());
                    existing.setUnit(product.getUnit());
                    existing.setVat(product.getVat());
                    existing.setUserId(product.getUserId());
                    return repo.save(existing);
                })
                .orElse(null);
    }
}
