package com.example.POSApi.service;

import com.example.POSApi.model.Product;
import com.example.POSApi.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByUserId(int userId) {
        return productRepository.findByUserId(userId);
    }
}
