package com.example.POSApi.service;

import com.example.POSApi.model.SaleItem;
import com.example.POSApi.respository.SaleItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemService(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    public List<SaleItem> getAllSaleItems() {
        return saleItemRepository.findAll();
    }

    public List<SaleItem> getSaleItemsBySaleId(Long saleId) {
        return saleItemRepository.findBySaleId(saleId);
    }

    public Optional<SaleItem> getSaleItemById(Long id) {
        return saleItemRepository.findById(id);
    }

    public SaleItem createSaleItem(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public void deleteSaleItem(Long id) {
        saleItemRepository.deleteById(id);
    }
}
