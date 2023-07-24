package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.security.LoginResponse;
import com.ekrema.spring.coupon.system.security.User;

public interface AuthService {
    LoginResponse login(User user) throws CouponSystemException;
}
