package com.assessment.wisanimabasa.controller.rest;


import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * The type Product controller.
 *  @author Wisani Mabasa
 *  @Since 22-sep-2023
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all product.
     *
     * @return the all product
     */
    @GetMapping
    @Operation(summary = "Get product list", description = "Get product details by their unique productId")
    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema =  @Schema(implementation = ProductDTO.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProducts();
    }

    /**
     * Gets product id.
     *
     * @param productId the product id
     * @return the product id
     */
    @GetMapping("/{productId}")
    @Operation(summary = "Get product by productId", description = "Get product details by their unique productId")
    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema =  @Schema(implementation = ProductDTO.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductId(@PathVariable final UUID productId) {
        return productService.getProductId(productId);
    }

    /**
     * Create product product dto.
     *
     * @param productDTO the product dto
     * @return the product dto
     */
    @PostMapping
    @Operation(summary = "Create product", description = "Create a new product")
    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema =  @Schema(implementation = ProductDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO createProduct(@RequestBody final ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }


}
