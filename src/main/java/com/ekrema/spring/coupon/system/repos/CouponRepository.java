package com.ekrema.spring.coupon.system.repos;

import com.ekrema.spring.coupon.system.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
}
