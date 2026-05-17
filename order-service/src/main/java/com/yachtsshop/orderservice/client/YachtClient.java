package com.yachtsshop.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Aici îi spunem la ce adresă să caute primul microserviciu
@FeignClient(name = "yacht-catalog", url = "${YACHT_CATALOG_URL:http://localhost:8081}")
public interface YachtClient {

    @GetMapping("/yachts/{id}")
    YachtDto getYachtById(@PathVariable("id") Long id);
}