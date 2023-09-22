package com.assessment.wisanimabasa.models.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;
@Embeddable
public class ProductId implements Serializable {
    private UUID productId;
    public UUID getProductId() {
        return this.productId;
    }
    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public ProductId(UUID productId) {
        this.productId = productId;
    }
    public ProductId() {
    }

}
