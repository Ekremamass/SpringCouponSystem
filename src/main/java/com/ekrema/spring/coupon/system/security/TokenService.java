package com.ekrema.spring.coupon.system.security;

import com.ekrema.spring.coupon.system.login.ClientType;

import java.util.UUID;

public interface TokenService {
    UUID addToken(User user);

    boolean isUserAllowed(UUID token, ClientType clientType);

    void clear();

    int getId(UUID token);
}
