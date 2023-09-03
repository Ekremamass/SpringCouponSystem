package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(int customerId, int id) throws CouponSystemException;

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCoupons(int customerId, Category category);

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice);

    Customer getCustomerDetails(int customerId) throws CouponSystemException;

    List<Coupon> getAllCoupons();

    List<Coupon> getLatestCoupons();
}
