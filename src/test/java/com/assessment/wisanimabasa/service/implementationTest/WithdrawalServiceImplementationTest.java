package com.assessment.wisanimabasa.service.implementationTest;

import com.assessment.wisanimabasa.entity.Investor;
import com.assessment.wisanimabasa.entity.Product;
import com.assessment.wisanimabasa.entity.Withdrawal;
import com.assessment.wisanimabasa.enums.ProductType;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.models.dto.WithdrawalDTO;
import com.assessment.wisanimabasa.repository.ProductRepository;
import com.assessment.wisanimabasa.repository.WithdrawalRepository;
import com.assessment.wisanimabasa.service.implementation.WithdrawalServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {WithdrawalServiceImplementation.class, WithdrawalRepository.class, Withdrawal.class})
class WithdrawalServiceImplementationTest {

    private final WithdrawalServiceImplementation withdrawalService;
    private Withdrawal withdrawal;

    private UUID withdrawalId = UUID.randomUUID();
    private UUID productId = UUID.randomUUID();
    private Product product;
    private Investor investor;
    private  WithdrawalDTO withdrawalDTO;


    @MockBean
    private WithdrawalRepository withdrawalRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    WithdrawalServiceImplementationTest(WithdrawalServiceImplementation withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @BeforeEach
    void createWithdrawal() {
        BigDecimal amount = new BigDecimal("500.00");
        BigDecimal withdrawalAmount = new BigDecimal("600.00");
        BigDecimal currentBalance = new BigDecimal("1000.00");
        withdrawalDTO = new WithdrawalDTO(
                withdrawalId,
                amount,
                Instant.now(),
                (product = new Product(new ProductDTO(
                                        productId,
                                        "wizzie",
                                        ProductType.RETIREMENT,
                                        currentBalance,
                                        withdrawalAmount,
                                        true,
                                         investor

                                )))
                );
    }

    @Test
    void testCreateWithdrawal() {
        when(productRepository.findById(productId)).thenReturn(product);
        // Call the createWithdrawal method
        ResponseEntity<String> response = withdrawalService.createWithdrawal(withdrawalDTO);

        // Check the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Withdrawal successful", response.getBody());

    }
}
