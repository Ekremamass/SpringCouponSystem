package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    Coupon addCoupon(int companyId, Coupon coupon) throws CouponSystemException;

    Coupon updateCoupon(int companyId, int id, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int companyId, int id) throws CouponSystemException;

    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getCompanyCoupons(int companyId, Category category);

    List<Coupon> getCompanyCoupons(int companyId, double maxPrice);

    Company getCompanyDetails(int companyId) throws CouponSystemException;
}
