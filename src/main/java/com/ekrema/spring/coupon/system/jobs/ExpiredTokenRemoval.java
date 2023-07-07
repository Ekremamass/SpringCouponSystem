package com.ekrema.spring.coupon.system.jobs;

import com.ekrema.spring.coupon.system.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpiredTokenRemoval {

    @Autowired
    private TokenService tokenService;

    @Scheduled(fixedRateString = "${token_interval}")
    public void clear() {
        tokenService.clear();
    }
}
