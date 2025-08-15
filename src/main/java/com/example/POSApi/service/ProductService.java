package com.example.POSApi.service;

import com.example.POSApi.model.Product;
import com.example.POSApi.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByUserId(int userId) {
        return productRepository.findByUserId(userId);
    }

    // Yeni ürün ekle
    public Product addProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    // Ürün güncelle
    public Product updateProduct(int id, Product product) {
        Optional<Product> existingOpt = productRepository.findById((long) id);
        if (existingOpt.isPresent()) {
            Product existing = existingOpt.get();
            existing.setName(product.getName());
            existing.setBarcode(product.getBarcode());
            existing.setPrice(product.getPrice());
            existing.setStock(product.getStock());
            existing.setPurchasePrice(product.getPurchasePrice());
            existing.setUnit(product.getUnit());
            existing.setVat(product.getVat());
            existing.setUserId(product.getUserId());
            return productRepository.save(existing);
        }
        return null;
    }


}
