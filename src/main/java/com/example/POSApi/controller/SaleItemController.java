package com.example.POSApi.controller;

import com.example.POSApi.model.SaleItem;
import com.example.POSApi.service.SaleItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale-items")
public class SaleItemController {

    private final SaleItemService saleItemService;

    public SaleItemController(SaleItemService saleItemService) {
        this.saleItemService = saleItemService;
    }

    @GetMapping
    public List<SaleItem> getAllSaleItems() {
        return saleItemService.getAllSaleItems();
    }

    @GetMapping("/sale/{saleId}")
    public List<SaleItem> getSaleItemsBySale(@PathVariable Long saleId) {
        return saleItemService.getSaleItemsBySaleId(saleId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleItem> getSaleItemById(@PathVariable Long id) {
        return saleItemService.getSaleItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SaleItem createSaleItem(@RequestBody SaleItem saleItem) {
        return saleItemService.createSaleItem(saleItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Long id) {
        saleItemService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }
}
