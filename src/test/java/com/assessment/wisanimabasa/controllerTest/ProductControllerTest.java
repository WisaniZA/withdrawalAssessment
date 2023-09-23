package com.assessment.wisanimabasa.controllerTest;

import com.assessment.wisanimabasa.controller.rest.ProductController;
import com.assessment.wisanimabasa.entities.Investor;
import com.assessment.wisanimabasa.entities.Product;
import com.assessment.wisanimabasa.enums.ProductType;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.service.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    private final UUID productId = UUID.randomUUID();
    private Product product;
    private Investor investor;
    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;
    @MockBean
    private ProductService productService;
@Autowired
    public ProductControllerTest(final ObjectMapper objectMapper, final MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
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
    void addProduct() throws Exception {

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(product.toProductDTO());

        String productJson = objectMapper.writeValueAsString(product.toProductDTO());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andReturn()
                .getResponse();

        TypeReference<ProductDTO> type = new TypeReference<>() {
        };
        ProductDTO responseObject = objectMapper.readValue(response.getContentAsString(), type);

        // Verify the response
        assertEquals(mapToJson(product.toProductDTO()), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }
    @Test
    void getProductId() throws Exception {
        when(productService.getProductId(productId)).thenReturn(product.toProductDTO());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/{productId}", product.toProductDTO().productId()))
                .andReturn().getResponse();

        TypeReference<ProductDTO> type = new TypeReference<>() {
        };
        ProductDTO responseObject = objectMapper.readValue(response.getContentAsString(), type);

        assertEquals(mapToJson(product.toProductDTO()), response.getContentAsString());
        assertEquals(200, response.getStatus());

    }
    @Test
    void getAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(product.toProductDTO()));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products"))
                .andReturn().getResponse();

        TypeReference<List<ProductDTO>> type = new TypeReference<>() {
        };
        List<ProductDTO> responseObject = objectMapper.readValue(response.getContentAsString(), type);

        // Verify the response
        assertEquals(mapToJson(Collections.singletonList(product.toProductDTO())), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }
    private String mapToJson(final Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
