package com.assessment.wisanimabasa.service.implementation;

import com.assessment.wisanimabasa.entity.Product;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.models.id.ProductId;
import com.assessment.wisanimabasa.repository.ProductRepository;
import com.assessment.wisanimabasa.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Product service implementation.
 * *  @author Wisani Mabasa
 *  *   @Since 22-sep-2023
 */
@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;

    /**
     * Instantiates a new Product service implementation.
     *
     * @param productRepository the product repository
     */
    @Autowired
    public ProductServiceImplementation(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(final ProductDTO productDTO) {
        Product product = new Product();
        return productRepository.save(product).toProductDTO();
    }

    @Override
    public ProductDTO getProductId(final UUID productId) {
        return getProductEntity(productId).toProductDTO();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream().map(Product::toProductDTO).toList();
    }
    private Product getProductEntity(final UUID productId){
        return productRepository.findById(new ProductId(productId))
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
    }
}
