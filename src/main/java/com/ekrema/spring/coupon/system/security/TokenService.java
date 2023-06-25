package com.ekrema.spring.coupon.system.security;

import com.ekrema.spring.coupon.system.beans.User;

import java.util.UUID;

public interface TokenService {
    UUID addToken(User user);
}
