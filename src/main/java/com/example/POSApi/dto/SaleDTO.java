package com.example.POSApi.dto;

import java.time.LocalDateTime;

public class SaleDTO {

    private Long id;
    private Long userId;
    private String customerName;
    private double totalAmount;
    private double paidAmount;
    private String payment_method;
    private LocalDateTime date;
    private double balance;
    private double total_discount;

    // Getter - Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }

    public double getBalance(){return balance;}
    public void  setBalance(double balance){this.balance = balance;}

    public String getPayment_method() { return payment_method; }
    public void setPayment_method(String payment_method) { this.payment_method = payment_method; }

    public double getTotal_discount(){return total_discount;}
    public void setTotal_discount(double total_discount){this.total_discount = total_discount;}

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
