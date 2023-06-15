package com.ekrema.spring.coupon.system.repos;

import com.ekrema.spring.coupon.system.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
