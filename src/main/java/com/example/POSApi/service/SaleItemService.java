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

    // ✅ Update metodu
    public SaleItem updateSaleItem(Long id, SaleItem updatedItem) {
        return saleItemRepository.findById(id).map(item -> {
            item.setSaleId(updatedItem.getSaleId());
            item.setProductId(updatedItem.getProductId());
            item.setProductName(updatedItem.getProductName());
            item.setQuantity(updatedItem.getQuantity());
            item.setUnitPrice(updatedItem.getUnitPrice());
            item.setTotalPrice(updatedItem.getTotalPrice());
            item.setBarcode(updatedItem.getBarcode());
            item.setDiscount(updatedItem.getDiscount());
            return saleItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("SaleItem not found with id: " + id));
    }
}
