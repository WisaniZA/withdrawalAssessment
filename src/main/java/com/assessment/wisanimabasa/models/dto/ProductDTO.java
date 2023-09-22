package com.assessment.wisanimabasa.models.dto;

import com.assessment.wisanimabasa.entity.Investor;
import com.assessment.wisanimabasa.enums.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(UUID productId, String productName, ProductType productType, BigDecimal currentBalance, BigDecimal withdrawalBalance, boolean isActive, Investor investor) {

    public ProductDTO(UUID productId, String productName, ProductType productType, BigDecimal currentBalance, BigDecimal withdrawalBalance, boolean isActive, Investor investor) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.currentBalance = currentBalance;
        this.withdrawalBalance = withdrawalBalance;
        this.isActive = isActive;
        this.investor = investor;
    }
    public UUID productId() {
        return this.productId;
    }
    public String productName() {
        return this.productName;
    }
    public ProductType productType() {
        return this.productType;
    }
    public BigDecimal currentBalance() {
        return this.currentBalance;
    }
    public BigDecimal withdrawalBalance() {
        return this.withdrawalBalance;
    }
    public boolean isActive(){
        return this.isActive;
    }
    public Investor investor(){
        return this.investor;
    }
}
