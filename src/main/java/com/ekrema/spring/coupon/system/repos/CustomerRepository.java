package com.ekrema.spring.coupon.system.repos;

import com.ekrema.spring.coupon.system.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
