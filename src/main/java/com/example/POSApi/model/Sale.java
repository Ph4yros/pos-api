package com.example.POSApi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;           // Kullanıcıya bağlamak için

    @Column(name = "customer_id")
    private Long customerId;      // Customer tablosu ile ilişkili

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "payment_method")
    private String payment_method; // Veritabanındaki isim

    @Column(name = "date")
    private LocalDateTime date;    // Veritabanındaki tarih

    @Column(name = "balance")
    private double balance;

    @Column(name = "total_discount")
    private double total_discount;

    // Getter - Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

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
