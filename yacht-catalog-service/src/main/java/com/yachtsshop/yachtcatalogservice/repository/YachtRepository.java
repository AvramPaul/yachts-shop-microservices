package com.yachtsshop.yachtcatalogservice.repository;

import com.yachtsshop.yachtcatalogservice.model.Yacht;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YachtRepository extends JpaRepository<Yacht, Long> {
}
