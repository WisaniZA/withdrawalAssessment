package com.assessment.wisanimabasa.controller.rest;

import com.assessment.wisanimabasa.entities.Investor;
import com.assessment.wisanimabasa.entities.Product;
import com.assessment.wisanimabasa.models.dto.InvestorDTO;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.service.interfaces.InvestorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * The type Investor controller.
 * @author Wisani Mabasa
 * @Since 22-sep-2023
 */
@RestController
@RequestMapping("/api/investors")
@CrossOrigin
public class InvestorController {

    private final InvestorService investorService;

    /**
     * Instantiates a new Investor controller.
     *
     * @param investorService the investor service
     */
    @Autowired
    public InvestorController(final InvestorService investorService) {
        this.investorService = investorService;
    }

    /**
     * Gets investor info.
     *
     * @param investorId the investor id
     * @return the investor info
     */
    @GetMapping("/{investorId}")
    @Operation(summary = "Get investor by investorId", description = "Get investor details by their unique investorId")
    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema =  @Schema(implementation = Investor.class)))
    @ApiResponse(responseCode = "404", description = "Investor not found.")
    @ResponseStatus(HttpStatus.OK)
    public InvestorDTO getInvestorInfo(final @PathVariable UUID investorId) {
        return investorService.getInvestorInfo(investorId);
    }

    /**
     * Gets investor products.
     *
     * @param investorId the investor id
     * @return the investor products
     */
    @GetMapping("/{investorId}/products")
    @Operation(summary = "Get investor products by investorId and product", description = "Get investor products by their unique investorId and products")
    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema =  @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Investor not found.")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getInvestorProducts(@PathVariable UUID investorId) {
        return investorService.getInvestorProducts(investorId);
    }
}
