package com.yachtsshop.yachtcatalogservice.controller;

import com.yachtsshop.yachtcatalogservice.model.Yacht;
import com.yachtsshop.yachtcatalogservice.repository.YachtRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/yachts")
public class YachtController {

    private final YachtRepository yachtRepository;

    public YachtController(YachtRepository yachtRepository) {
        this.yachtRepository = yachtRepository;
    }

    // Returnează toate iahturile
    @GetMapping
    public List<Yacht> getAllYachts() {
        return yachtRepository.findAll();
    }

    // Returnează un singur iaht după ID (ne va trebui pentru Order Service!)
    @GetMapping("/{id}")
    public Yacht getYachtById(@PathVariable Long id) {
        return yachtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Iahtul nu a fost găsit!"));
    }
}