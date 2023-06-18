package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;

import java.util.List;
public interface CustomerService {
    void purchaseCoupon(Coupon coupon) throws CouponSystemException;

    List<Coupon> getCustomerCoupons();

    List<Coupon> getCustomerCoupons(Category category);

    List<Coupon> getCustomerCoupons(double maxPrice);

    Customer getCustomerDetails() throws CouponSystemException;
}
