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

            // CustomerName ekle
            Customer customer = customerRepository.findById(sale.getCustomerId()).orElse(null);
            dto.setCustomerName(customer != null ? customer.getName() : "Bilinmiyor");

            dtoList.add(dto);
        }

        return dtoList;
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
}
