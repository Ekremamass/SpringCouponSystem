package com.ekrema.spring.coupon.system.repos;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO spring_coupon_system.customers_coupons (`customer_id`, `coupons_id`) VALUES (?, ?)", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);

    boolean existsByCompany_idAndTitle(int companyId, String title);
    List<Coupon> findByCompany_id(int companyId);
    List<Coupon> findByCompany_idAndCategory(int companyId, Category category);
    List<Coupon> findByCompany_idAndPriceLessThanEqual(int companyId, double MaxPrice);
}
