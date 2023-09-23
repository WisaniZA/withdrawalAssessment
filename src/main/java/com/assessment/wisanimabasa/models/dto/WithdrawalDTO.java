package com.assessment.wisanimabasa.models.dto;



import com.assessment.wisanimabasa.entities.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WithdrawalDTO(UUID withdrawalId, BigDecimal amount, Instant withdrawalDate, Product product) {

    public WithdrawalDTO(UUID withdrawalId, BigDecimal amount, Instant withdrawalDate, Product product) {
        this.withdrawalId = withdrawalId;
        this.amount = amount;
        this.withdrawalDate = withdrawalDate;
        this.product = product;

    }

    public UUID withdrawalId() {
        return this.withdrawalId;
    }

    public BigDecimal amount() {
        return this.amount;
    }

    public Instant withdrawalDate() {
        return this.withdrawalDate;
    }

    public Product product() {
        return this.product;
    }
}
