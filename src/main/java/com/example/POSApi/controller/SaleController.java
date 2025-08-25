package com.example.POSApi.controller;

import com.example.POSApi.dto.SaleDTO;
import com.example.POSApi.model.Sale;
import com.example.POSApi.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/user/{userId}")
    public List<SaleDTO> getSalesByUser(@PathVariable Long userId) {
        return saleService.getSalesByUserId(userId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<SaleDTO> getSalesByCustomer(@PathVariable Long customerId) {
        return saleService.getSalesByCustomerId(customerId);
    }


    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        sale.setBalance(sale.getTotalAmount() - sale.getPaidAmount());
        return saleService.createSale(sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        sale.setBalance(sale.getTotalAmount() - sale.getPaidAmount());

        try {
            Sale updatedSale = saleService.updateSale(id, sale);
            return ResponseEntity.ok(updatedSale);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
