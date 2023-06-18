package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int id, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int id) throws CouponSystemException;

    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(Category category);

    List<Coupon> getCompanyCoupons(double maxPrice);

    Company getCompanyDetails() throws CouponSystemException;
}
