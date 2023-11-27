package com.example.springbatch.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
public class Customers {
    @Column(name = "Id")
    private Long index;
    private String customerId;
    private String firstName;
    private String lastName;
    private String company;
    private String city;
    private String country;
    private String phoneNumber1;
    private String phoneNumber2;
    private String email;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "date")
    private Date subscriptionDate;
    private String website;



}
