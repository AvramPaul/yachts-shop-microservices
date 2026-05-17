package com.yachtsshop.orderservice.controller;

import com.yachtsshop.orderservice.client.YachtClient;
import com.yachtsshop.orderservice.client.YachtDto;
import com.yachtsshop.orderservice.model.Order;
import com.yachtsshop.orderservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final YachtClient yachtClient;

    public OrderController(OrderRepository orderRepository, YachtClient yachtClient) {
        this.orderRepository = orderRepository;
        this.yachtClient = yachtClient;
    }

    // Endpoint pentru a plasa o comandă
    @PostMapping("/place/{yachtId}")
    public Order placeOrder(@PathVariable Long yachtId, @RequestParam String customerName) {
        // 1. Apelăm microserviciul de Catalog pentru a vedea dacă iahtul există
        YachtDto yacht = yachtClient.getYachtById(yachtId);

        // 2. Creăm comanda folosind datele aduse din celălalt serviciu
        Order newOrder = new Order(
                customerName,
                yacht.id(),
                yacht.name(),
                yacht.price()
        );

        // 3. Salvăm și returnăm comanda
        return orderRepository.save(newOrder);
    }

    // Endpoint pentru a vedea toate comenzile
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}