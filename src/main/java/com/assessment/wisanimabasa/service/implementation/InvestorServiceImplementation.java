package com.assessment.wisanimabasa.service.implementation;

import com.assessment.wisanimabasa.entities.Investor;
import com.assessment.wisanimabasa.entities.Product;
import com.assessment.wisanimabasa.exceptions.InvestorNotFoundException;
import com.assessment.wisanimabasa.models.dto.InvestorDTO;
import com.assessment.wisanimabasa.models.dto.ProductDTO;
import com.assessment.wisanimabasa.repository.InvestRepository;
import com.assessment.wisanimabasa.service.interfaces.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Investor service implementation.
 *  @author Wisani Mabasa
 *  *   @Since 22-sep-2023
 */
@Service
public class InvestorServiceImplementation implements InvestorService {
    private final InvestRepository investRepository;

    /**
     * Instantiates a new Investor service implementation.
     *
     * @param investRepository the invest repository
     */
    @Autowired
    public InvestorServiceImplementation(final InvestRepository investRepository) {
        this.investRepository = investRepository;

    }
    /**
     * Retrieves detailed information about a specific investor based on their unique identifier.
     *
     * @param investorId The unique identifier of the investor.
     * @return An InvestorDTO object containing the investor's personal and contact information.
     * @throws InvestorNotFoundException If no investor is found with the specified ID.
     */
    public InvestorDTO getInvestorInfo(final UUID investorId) {
        // Retrieve the investor
        Investor investor = investRepository.findById(investorId);

        // Check if the investor exists
        if (investor == null) {
            throw new InvestorNotFoundException("Investor not found with ID: " + investorId);
        }

        Investor investorInfo = new Investor();
        investorInfo.setInvestorId(investor.getInvestorId());
        investorInfo.setFirstName(investor.getFirstName());
        investorInfo.setLastName(investor.getLastName());
        investorInfo.setAge(investor.getAge());
        investorInfo.setAddress(investor.getAddress());
        investorInfo.setEmail(investor.getEmail());
        investorInfo.setContactNumber(investor.getContactNumber());

        return investorInfo.toInveStorDTO();
    }
    /**
     * Retrieves a list of product details associated with a specific investor.
     *
     * @param investorId The unique identifier of the investor.
     * @return A list of ProductDTO objects representing the investor's products.
     * @throws InvestorNotFoundException If no investor is found with the specified ID.
     */
    public List<ProductDTO> getInvestorProducts(final UUID investorId) {
        // Retrieve the investor
        Investor investor = investRepository.findById(investorId);

        // Check if the investor exists
        if (investor == null) {
            throw new InvestorNotFoundException("Investor not found with ID: " + investorId);
        }

        // Retrieve the products associated with the investor
        List<Product> investorProducts = investor.getProducts();

        // Create a list to store the product details
        List<ProductDTO> productDetailsList = new ArrayList<>();

        // Iterate through the investor's products and fetch the required details
        for (Product product : investorProducts) {
            Product productDetails = new Product();
            productDetails.setProductId(product.getProductId());
            productDetails.setProductName(product.getProductName());
            productDetails.setProductType(product.getProductType());
            productDetails.setCurrentBalance(product.getCurrentBalance());
            productDetails.setWithdrawalBalance(product.getWithdrawalBalance());
            productDetails.setActive(product.isActive());
            productDetailsList.add(productDetails.toProductDTO());
        }

        return productDetailsList;
    }
}
