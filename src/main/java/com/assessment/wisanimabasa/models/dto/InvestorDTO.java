package com.assessment.wisanimabasa.models.dto;



import com.assessment.wisanimabasa.entities.Product;

import java.util.List;
import java.util.UUID;

public record InvestorDTO(UUID investorId, String firstName, String lastName, int age, String email, String address, String contactNumber, List<Product> products) {
    public InvestorDTO(UUID investorId, String firstName, String lastName, int age, String email, String address, String contactNumber, List<Product> products) {
        this.investorId = investorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.products = products;


    }

    @Override
    public UUID investorId() {
        return investorId;
    }

    public String firstName() {return this.firstName;};

    public String lastName() {return this.lastName;
    }
    public int age() {return this.age;}
    public String email() {return this.email;}
    public String address() {return this.address;}
    public String contactNumber() {return this.contactNumber;}
    public List<Product> products() {return this.products;}
}
