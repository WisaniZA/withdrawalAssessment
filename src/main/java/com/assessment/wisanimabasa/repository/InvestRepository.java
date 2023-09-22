package com.assessment.wisanimabasa.repository;

import com.assessment.wisanimabasa.entity.Investor;
import com.assessment.wisanimabasa.models.id.InvestorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvestRepository extends JpaRepository<Investor, InvestorId> {
    Investor findById(UUID InvestorId);
}
