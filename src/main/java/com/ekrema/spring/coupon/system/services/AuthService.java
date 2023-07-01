package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.security.User;

import java.util.UUID;

public interface AuthService {
    UUID login(User user) throws CouponSystemException;
}
