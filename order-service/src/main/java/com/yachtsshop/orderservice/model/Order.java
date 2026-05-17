package com.yachtsshop.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders") // "order" este cuvânt rezervat în SQL, așa că folosim "orders"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private Long yachtId;
    private String yachtName; // Salvăm numele și prețul la momentul cumpărării!
    private Double pricePaid;

    public Order() {}

    public Order(String customerName, Long yachtId, String yachtName, Double pricePaid) {
        this.customerName = customerName;
        this.yachtId = yachtId;
        this.yachtName = yachtName;
        this.pricePaid = pricePaid;
    }

    // Getteri
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public Long getYachtId() { return yachtId; }
    public String getYachtName() { return yachtName; }
    public Double getPricePaid() { return pricePaid; }
}