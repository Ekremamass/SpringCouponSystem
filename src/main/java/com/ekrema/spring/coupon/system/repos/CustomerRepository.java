package com.ekrema.spring.coupon.system.repos;

import com.ekrema.spring.coupon.system.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);

    @Query(value = "SELECT id FROM spring_coupon_system.customers WHERE email = ?;",nativeQuery = true)
    int getIdByEmail(String email);

}
