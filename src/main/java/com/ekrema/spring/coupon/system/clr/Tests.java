package com.ekrema.spring.coupon.system.clr;

import com.ekrema.spring.coupon.system.tests.AdminServiceTest;
import com.ekrema.spring.coupon.system.repos.CouponRepository;
import com.ekrema.spring.coupon.system.tests.CompanyServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Tests implements CommandLineRunner {
    @Autowired
    private AdminServiceTest adminServiceTest;
    @Autowired
    private CompanyServiceTest companyServiceTest;


    @Override
    public void run(String... args) throws Exception {
        adminServiceTest.testAsAdmin();
        companyServiceTest.testAsCompany();


    }
}
