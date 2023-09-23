package com.assessment.wisanimabasa.service.implementationTest;

import com.assessment.wisanimabasa.entities.Investor;
import com.assessment.wisanimabasa.entities.Product;
import com.assessment.wisanimabasa.enums.ProductType;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.models.id.ProductId;
import com.assessment.wisanimabasa.repository.ProductRepository;
import com.assessment.wisanimabasa.service.implementation.ProductServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProductServiceImplementation.class, ProductRepository.class, Product.class})
class ProductServiceImplementationTest {

    private final ProductServiceImplementation productService;
    private final UUID productId = UUID.randomUUID();
    private Product product;
    private Investor investor;
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    ProductServiceImplementationTest(final ProductServiceImplementation productService) {
        this.productService = productService;
    }

    @BeforeEach
    void createProduct() {
        BigDecimal withdrawalAmount = new BigDecimal("600.00");
        BigDecimal currentBalance = new BigDecimal("500.00");
        product = new Product(new ProductDTO(
                productId,
                "wizzie",
                ProductType.RETIREMENT,
                currentBalance,
                withdrawalAmount,
                true,
                investor

        ));
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductDTO returnedProduct = productService.createProduct(product.toProductDTO());
        assertEquals(productId, returnedProduct.productId());
        assertEquals(new BigDecimal("500.00"), product.getCurrentBalance());
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<ProductDTO> returnedProducts = productService.getAllProducts();
        assertEquals(1, returnedProducts.size());
        assertEquals(product.toProductDTO(), returnedProducts.get(0));
    }

    @Test
    void testGetProductById() {

        when(productRepository.findById(any(ProductId.class))).thenReturn(Optional.ofNullable(product));
        ProductDTO returnedProduct = productService.getProductId(productId);
        assertEquals(productId, returnedProduct.productId());
    }


}
