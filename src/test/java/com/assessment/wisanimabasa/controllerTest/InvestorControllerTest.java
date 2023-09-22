package com.assessment.wisanimabasa.controllerTest;

import com.assessment.wisanimabasa.controller.rest.InvestorController;
import com.assessment.wisanimabasa.entity.Investor;
import com.assessment.wisanimabasa.entity.Product;
import com.assessment.wisanimabasa.enums.ProductType;
import com.assessment.wisanimabasa.models.dto.InvestorDTO;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.service.interfaces.InvestorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvestorController.class)
public class InvestorControllerTest {

    private  UUID investorId = UUID.randomUUID();
    private  UUID productId = UUID.randomUUID();
    private Investor investor;
    @MockBean
    private  Product product;
    private final ObjectMapper objectMapper;
    private MockMvc mockMvc;
    @InjectMocks
    private InvestorController investorController;

    @MockBean
    private InvestorService investorService;


@Autowired
    public InvestorControllerTest(final ObjectMapper objectMapper, final MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }
    @BeforeEach
    void createInvestor() {

        List<Product> investorProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID());
        product1.setProductName("Product 1");
        product1.setProductType(ProductType.RETIREMENT);
        product1.setCurrentBalance(BigDecimal.valueOf(1000.00));
        product1.setWithdrawalBalance(BigDecimal.valueOf(900.00));
        product1.setInvestor(investor);
        product1.setActive(true);
        investorProducts.add(product1);
        investor = new Investor(new InvestorDTO(
                investorId,
                "wisani",
                "Mabasa",
                19,
                "wisani@Gmail.com",
                "clyvilles",
                "0737033899",
                investorProducts
        ));


    }

    @Test
    void testGetInvestorInfo() throws Exception{

        when(investorService.getInvestorInfo(investorId)).thenReturn(investor.toInveStorDTO());
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/investors/{investorId}", investorId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(mapToJson(investor.toInveStorDTO()), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testGetInvestorProducts() throws Exception{
        BigDecimal withdrawalAmount = new BigDecimal("600.00");
        BigDecimal currentBalance = new BigDecimal("1000.00");
        List<ProductDTO> mokInvestorProducts = new ArrayList<>();
        Product product = new Product();
        product.setProductId(productId);
        product.setInvestor(investor);
        product.setWithdrawalBalance(withdrawalAmount);
        product.setCurrentBalance(currentBalance);
        product.setActive(true);
        product.setProductName("wizzie");
        product.setProductType(ProductType.RETIREMENT);
        mokInvestorProducts.add(product.toProductDTO());
        when(investorService.getInvestorProducts(investorId)).thenReturn(mokInvestorProducts);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/investors/{investorId}/products", investorId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(mapToJson(mokInvestorProducts), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    private String mapToJson(final Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
