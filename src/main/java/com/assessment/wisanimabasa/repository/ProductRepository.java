package com.assessment.wisanimabasa.repository;


import com.assessment.wisanimabasa.entity.Product;
import com.assessment.wisanimabasa.models.id.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {
    Product findById(UUID ProductId);
}
