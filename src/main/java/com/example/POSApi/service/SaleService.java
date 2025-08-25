package com.example.POSApi.service;

import com.example.POSApi.dto.SaleDTO;
import com.example.POSApi.model.Sale;
import com.example.POSApi.model.Customer;
import com.example.POSApi.respository.SaleRepository;
import com.example.POSApi.respository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;

    public SaleService(SaleRepository saleRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
    }

    // Tüm satışlar (DTO dönüşü olmadan)
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // UserId’ye göre satışlar (DTO ile, customerName ekli)
    public List<SaleDTO> getSalesByUserId(Long userId) {
        List<Sale> sales = saleRepository.findByUserId(userId);
        List<SaleDTO> dtoList = new ArrayList<>();

        for (Sale sale : sales) {
            SaleDTO dto = new SaleDTO();
            dto.setId(sale.getId());
            dto.setUserId(sale.getUserId());
            dto.setTotalAmount(sale.getTotalAmount());
            dto.setPaidAmount(sale.getPaidAmount());
            dto.setPayment_method(sale.getPayment_method());
            dto.setDate(sale.getDate());
            dto.setBalance((sale.getTotalAmount())-(sale.getPaidAmount()));
            dto.setTotal_discount(sale.getTotal_discount());

            // CustomerName ekle
            Customer customer = customerRepository.findById(sale.getCustomerId()).orElse(null);
            dto.setCustomerName(customer != null ? customer.getName() : "Bilinmiyor");

            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<SaleDTO> getSalesByCustomerId(Long customerId) {
        List<Sale> sales = saleRepository.findByCustomerId(customerId);
        return sales.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    private SaleDTO convertToDTO(Sale sale) {
        SaleDTO dto = new SaleDTO();
        dto.setId(sale.getId());
        dto.setUserId(sale.getUserId());
        dto.setTotalAmount(sale.getTotalAmount());
        dto.setPaidAmount(sale.getPaidAmount());
        dto.setPayment_method(sale.getPayment_method());
        dto.setDate(sale.getDate());
        dto.setBalance((sale.getTotalAmount())-(sale.getPaidAmount()));
        dto.setTotal_discount(sale.getTotal_discount());

        // CustomerName ekle
        Customer customer = customerRepository.findById(sale.getCustomerId()).orElse(null);
        dto.setCustomerName(customer != null ? customer.getName() : "Bilinmiyor");

        return dto;
    }

    public Sale updateSale(Long id, Sale sale) {
        return saleRepository.findById(id).map(existingSale -> {
            existingSale.setTotalAmount(sale.getTotalAmount());
            existingSale.setPaidAmount(sale.getPaidAmount());
            existingSale.setBalance(sale.getTotalAmount() - sale.getPaidAmount());
            existingSale.setPayment_method(sale.getPayment_method());
            existingSale.setDate(sale.getDate());
            existingSale.setTotal_discount(sale.getTotal_discount());
            existingSale.setCustomerId(sale.getCustomerId());
            return saleRepository.save(existingSale);
        }).orElseThrow(() -> new RuntimeException("Sale not found with id " + id));
    }


    public Sale updateSaleTotalAmount(Long id, Double totalAmount) {
        return saleRepository.findById(id).map(existingSale -> {
            existingSale.setTotalAmount(totalAmount);
            existingSale.setBalance(totalAmount - existingSale.getPaidAmount());
            return saleRepository.save(existingSale);
        }).orElseThrow(() -> new RuntimeException("Sale not found with id " + id));
    }

}
