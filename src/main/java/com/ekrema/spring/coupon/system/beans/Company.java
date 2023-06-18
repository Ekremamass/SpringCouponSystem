package com.ekrema.spring.coupon.system.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;

}
