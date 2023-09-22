package com.assessment.wisanimabasa.service.interfaces;

import com.assessment.wisanimabasa.models.dto.InvestorDTO;
import com.assessment.wisanimabasa.models.dto.ProductDTO;

import java.util.List;
import java.util.UUID;


public interface InvestorService {

    InvestorDTO getInvestorInfo(UUID investorId);
    List<ProductDTO> getInvestorProducts(UUID investorId);



}
