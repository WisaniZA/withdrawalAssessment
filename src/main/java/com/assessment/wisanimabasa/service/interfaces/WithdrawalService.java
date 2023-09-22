package com.assessment.wisanimabasa.service.interfaces;

import com.assessment.wisanimabasa.models.dto.WithdrawalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface WithdrawalService {

    ResponseEntity<String> createWithdrawal(WithdrawalDTO withdrawalDTO);
}
