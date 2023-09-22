package com.assessment.wisanimabasa.service.implementationTest;

import com.assessment.wisanimabasa.entity.Investor;
import com.assessment.wisanimabasa.entity.Product;
import com.assessment.wisanimabasa.enums.ProductType;
import com.assessment.wisanimabasa.exceptions.InvestorNotFoundException;
import com.assessment.wisanimabasa.models.dto.InvestorDTO;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.repository.InvestRepository;
import com.assessment.wisanimabasa.service.implementation.InvestorServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {InvestorServiceImplementation.class, InvestRepository.class, Investor.class, InvestorNotFoundException.class})
class InvestorServiceImplementationTest {

    private final InvestorServiceImplementation investorService;

    private Investor investor;
@MockBean
    private List<Product> product;
    private UUID investorId = UUID.randomUUID();
    private UUID productId = UUID.randomUUID();
    @MockBean
    private InvestRepository investRepository;
    @MockBean
    private InvestorNotFoundException investorNotFoundException;

    @Autowired
    public InvestorServiceImplementationTest(final InvestorServiceImplementation investorService) {
        this.investorService = investorService;
    }

    @BeforeEach
    void createInvestor() {
        BigDecimal withdrawalAmount = new BigDecimal("600.00");
        BigDecimal currentBalance = new BigDecimal("1000.00");
        List<Product> investorProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID());
        product1.setProductName("Product 1");
        product1.setProductType(ProductType.RETIREMENT);
        product1.setCurrentBalance(BigDecimal.valueOf(1000.00));
        product1.setWithdrawalBalance(BigDecimal.valueOf(900.00));
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
    void testGetInvestorInfo() {
        when(investRepository.findById(investorId)).thenReturn(investor);
        InvestorDTO returnedValue = investorService.getInvestorInfo(investorId);
        assertEquals(investorId, returnedValue.investorId());
        assertEquals("wisani" ,returnedValue.firstName());
    }

    @Test
    void testGetInvestorProducts(){
        List<Product> investorProducts = new ArrayList<>();
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        product.setProductName("Wisani");
        product.setProductType(ProductType.RETIREMENT);
        product.setCurrentBalance(BigDecimal.valueOf(1000.00));
        product.setWithdrawalBalance(BigDecimal.valueOf(900.00));
        product.setActive(true);
        investorProducts.add(product);

        when(investRepository.findById(investorId)).thenReturn(investor);
       investor.setProducts(investorProducts);

       List<ProductDTO> productDTOList = investorService.getInvestorProducts(investorId);
       ProductDTO returnProductDTO = productDTOList.get(0);

       assertEquals(product.getProductId(), returnProductDTO.productId() );
       assertEquals(product.getProductType(),returnProductDTO.productType());
       assertEquals(product.getCurrentBalance(), returnProductDTO.currentBalance());
       assertTrue(product.isActive(), String.valueOf(returnProductDTO.isActive()));
    }
}
