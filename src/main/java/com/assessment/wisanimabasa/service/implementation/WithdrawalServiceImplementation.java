package com.assessment.wisanimabasa.service.implementation;

import com.assessment.wisanimabasa.entities.Product;
import com.assessment.wisanimabasa.entities.Withdrawal;
import com.assessment.wisanimabasa.exceptions.ProductNotFoundException;
import com.assessment.wisanimabasa.models.dto.WithdrawalDTO;
import com.assessment.wisanimabasa.repository.ProductRepository;
import com.assessment.wisanimabasa.repository.WithdrawalRepository;
import com.assessment.wisanimabasa.service.interfaces.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * The type Withdrawal service implementation.
 * *  @author Wisani Mabasa
 *  *   @Since 22-sep-2023
 */
@Service
public class WithdrawalServiceImplementation implements WithdrawalService {

    private final ProductRepository productRepository;
    private final WithdrawalRepository withdrawalRepository;

    /**
     * Instantiates a new Withdrawal service implementation.
     *
     * @param productRepository    the product repository
     * @param withdrawalRepository the withdrawal repository
     */
    @Autowired
    public WithdrawalServiceImplementation(final ProductRepository productRepository, final WithdrawalRepository withdrawalRepository) {

        this.productRepository = productRepository;
        this.withdrawalRepository = withdrawalRepository;
    }
    /**
     * Creates a withdrawal transaction for a specified product and validates the withdrawal amount.
     *
     * @param withdrawalDTO The WithdrawalDTO containing withdrawal details.
     * @return A ResponseEntity with a success message if the withdrawal is successful,
     *         or a bad request response if the withdrawal is invalid.
     * @throws ProductNotFoundException If no product is found with the specified product ID.
     */
    @Override
    public ResponseEntity<String> createWithdrawal(WithdrawalDTO withdrawalDTO) {

        UUID productId = withdrawalDTO.product().getProductId();
        BigDecimal withdrawalAmount = (withdrawalDTO.amount());

        Product product = productRepository.findById(productId);
        // Check if the investor and product exist
        if (product == null) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        // Check if the withdrawal amount is greater than the current balance
        if (withdrawalAmount.compareTo((product.getCurrentBalance())) > 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount exceeds current balance");
        }

        // Check if the withdrawal amount is more than 90% of the current balance
        BigDecimal maxWithdrawalAmount = product.getCurrentBalance().multiply(BigDecimal.valueOf(0.9));
        if (withdrawalAmount.compareTo(maxWithdrawalAmount) > 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount cannot exceed 90% of current balance");
        }

        // Perform withdrawal logic and update the product's balance
        BigDecimal newBalance = product.getCurrentBalance().subtract(withdrawalAmount);
        product.setCurrentBalance(newBalance);
        productRepository.save(product);

        // Create a withdrawal transaction
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setWithdrawalId(productId);
        withdrawal.setProduct(product);
        withdrawal.setWithdrawalDate(Instant.now());
        withdrawal.setAmount(withdrawalAmount);
        withdrawalRepository.save(withdrawal);

        return ResponseEntity.ok("Withdrawal successful");
    }
}

