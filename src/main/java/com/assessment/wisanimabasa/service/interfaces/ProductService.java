package com.assessment.wisanimabasa.service.interfaces;


import com.assessment.wisanimabasa.models.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductId(UUID productId);
    List<ProductDTO> getAllProducts();

}
