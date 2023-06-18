package com.ekrema.spring.coupon.system.beans;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;

    private double price;
    private String image;
}
