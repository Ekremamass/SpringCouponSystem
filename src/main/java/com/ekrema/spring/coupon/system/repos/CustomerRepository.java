package com.ekrema.spring.coupon.system.repos;

import com.ekrema.spring.coupon.system.beans.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    @Query(value = "SELECT id FROM customers WHERE email = ?;", nativeQuery = true)
    int getIdByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_coupons WHERE customer_id = ?", nativeQuery = true)
    void deletePurchaseByCustomerId(int id);
}
