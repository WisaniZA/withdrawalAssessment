package com.assessment.wisanimabasa.entities;

import com.assessment.wisanimabasa.enums.ProductType;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.models.id.ProductId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Setter
@Getter
@NoArgsConstructor
@IdClass(ProductId.class)
public class Product {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID productId;

    @Column
    private String productName;

    @Column(columnDefinition = "varchar(255)")
    private ProductType productType;

    @Column(columnDefinition = "numeric(10, 2)")
    private BigDecimal currentBalance;

    @Column(columnDefinition = "numeric(10, 2)")
    private BigDecimal withdrawalBalance;

    @Column(columnDefinition = "boolean")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "investor_investor_id")
    private Investor investor;

    public Product(ProductDTO productDTO) {
        this.productId = productDTO.productId();
        this.productName = productDTO.productName();
        this.productType = productDTO.productType();
        this.currentBalance= productDTO.currentBalance();
        this.withdrawalBalance = productDTO.withdrawalBalance();
        this.isActive = productDTO.isActive();
        this.investor = productDTO.investor();
    }
    public ProductDTO toProductDTO() {
        return new ProductDTO(this.productId, this.productName, this.productType, this.currentBalance, this.withdrawalBalance, this.isActive, this.investor);
    }
}
