package com.assessment.wisanimabasa.models.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class WithdrawalId implements Serializable {

    private UUID withdrawalId;

    public WithdrawalId() {
    }

    public  UUID getWithdrawalId() {
        return this.withdrawalId;
    }

    public void setWithdrawalId(UUID withdrawalId) {
        this.withdrawalId = withdrawalId;
    }
    public WithdrawalId(UUID withdrawalId) {
        this.withdrawalId = withdrawalId;
    }
}

