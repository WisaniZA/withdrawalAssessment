package com.assessment.wisanimabasa.controllerTest;

import com.assessment.wisanimabasa.controller.rest.WithdrawalController;
import com.assessment.wisanimabasa.entity.Product;
import com.assessment.wisanimabasa.entity.Withdrawal;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.models.dto.WithdrawalDTO;
import com.assessment.wisanimabasa.service.interfaces.WithdrawalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WithdrawalController.class)
public class WithdrawalControllerTest {

    private final ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private UUID withdrawalId = UUID.randomUUID();
    private UUID productId = UUID.randomUUID();
    private Product product;
    private Withdrawal withdrawal;

    @InjectMocks
    private WithdrawalController withdrawalController;
    @MockBean
    private WithdrawalService withdrawalService;

@Autowired
    public WithdrawalControllerTest(final ObjectMapper objectMapper, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }
    @BeforeEach
    void createWithdrawal() {
        BigDecimal amount = new BigDecimal("500.00");
        BigDecimal withdrawalAmount = new BigDecimal("600.00");
        BigDecimal currentBalance = new BigDecimal("1000.00");
        withdrawal =new Withdrawal( new WithdrawalDTO(
                withdrawalId,
                amount,
                Instant.now(),
                product
        ));
    }
    @Test
    void testCreateWithdrawal() throws Exception{
       Withdrawal withdrawal1 = new Withdrawal();
        withdrawal1.setProduct(product);
        withdrawal1.setAmount(BigDecimal.valueOf(500.00));
        withdrawal1.setWithdrawalDate(Instant.now());
        when(withdrawalService.createWithdrawal(withdrawal1.toWithdrawalDTO())).thenReturn(new ResponseEntity<>("Withdrawal successful", HttpStatus.OK));

        // Perform the POST request to the controller endpoint
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/withdrawals/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"productId\": \"" + productId + "\", \"amount\": 500.00 }")
        ).andReturn().getResponse();

        // Assert the expected status code and response content
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Withdrawal successful", response.getContentAsString());
    }


    private String mapToJson(final Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
