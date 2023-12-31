package com.ekrema.spring.coupon.system.jobs;

import com.ekrema.spring.coupon.system.repos.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class CouponExpirationDailyJob {

    @Autowired
    private CouponRepository couponRepository;

    @Transactional
    @Scheduled(fixedRateString = "${coupon_interval}")
    public void deleteExpiredCoupon() throws InterruptedException {
        Thread.sleep(5000);
        couponRepository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));
    }
}
