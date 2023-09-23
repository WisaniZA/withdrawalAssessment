package com.assessment.wisanimabasa.entities;

import com.assessment.wisanimabasa.models.dto.InvestorDTO;
import com.assessment.wisanimabasa.models.id.InvestorId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InvestorId.class)
public class Investor {
    @Id
    private UUID investorId;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String address;
    private String contactNumber;
    private List<Product> products;


    public Investor(InvestorDTO investorDTO) {
        this.investorId = investorDTO.investorId();
        this.firstName = investorDTO.firstName();
        this.lastName = investorDTO.lastName();
        this.age = investorDTO.age();
        this.address = investorDTO.address();
        this.email = investorDTO.email();
        this.contactNumber = investorDTO.contactNumber();
        this.products = investorDTO.products();

    }

    public InvestorDTO toInveStorDTO() {
        return new InvestorDTO(
                this.investorId, this.firstName, this.lastName,
                this.age, this.email,
                this.address,
                this.contactNumber,
                this.products);
    }

}
