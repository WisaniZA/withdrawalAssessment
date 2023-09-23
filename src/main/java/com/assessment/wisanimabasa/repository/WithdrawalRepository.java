package com.assessment.wisanimabasa.repository;


import com.assessment.wisanimabasa.entities.Withdrawal;
import com.assessment.wisanimabasa.models.id.WithdrawalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, WithdrawalId> {


}
