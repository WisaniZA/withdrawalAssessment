package com.assessment.wisanimabasa.entity;


import com.assessment.wisanimabasa.enums.WithdrawalStatus;
import com.assessment.wisanimabasa.models.dto.WithdrawalDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID withdrawalId;
    private BigDecimal amount;
    private Instant withdrawalDate;
    @Enumerated(EnumType.STRING)

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Withdrawal(WithdrawalDTO withdrawalDTO) {
        this.withdrawalId = withdrawalDTO.withdrawalId();
        this.amount = withdrawalDTO.amount();
        this.withdrawalDate = withdrawalDTO.withdrawalDate();
        this.product = withdrawalDTO.product();
    }

    public WithdrawalDTO toWithdrawalDTO() {
        return new WithdrawalDTO(
                this.withdrawalId,
                this.amount,
                this.withdrawalDate,
                this.product
        );
    }

}
