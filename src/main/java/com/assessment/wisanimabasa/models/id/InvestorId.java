package com.assessment.wisanimabasa.models.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class InvestorId implements Serializable {

    private UUID investorId;

    public InvestorId() {
    }

    public UUID getInvestorId(){
        return this.investorId;
    }

    public void setInvestorId(UUID investorId) {
        this.investorId = investorId;
    }

    public InvestorId(UUID investorId){
        this.investorId = investorId;
    }
}
