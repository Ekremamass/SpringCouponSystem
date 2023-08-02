package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.security.LoginResponse;
import com.ekrema.spring.coupon.system.security.User;

public interface AuthService {
    LoginResponse login(User user) throws CouponSystemException;

    void registerCompany(Company company) throws CouponSystemException;

    void registerCustomer(Customer customer) throws CouponSystemException;
}
