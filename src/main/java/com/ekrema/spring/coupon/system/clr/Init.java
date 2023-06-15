package com.ekrema.spring.coupon.system.clr;

import com.ekrema.spring.coupon.system.Utils.FactoryUtils;
import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.repos.CompanyRepository;
import com.ekrema.spring.coupon.system.repos.CouponRepository;
import com.ekrema.spring.coupon.system.repos.CustomerRepository;
import com.ekrema.spring.coupon.system.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private FactoryUtils factoryUtils;


    @Override
    public void run(String... args) throws Exception {
        // add 10 companies
        companyRepository.saveAll(factoryUtils.initCompanies());
        couponRepository.saveAll(factoryUtils.initCoupons());
        customerRepository.saveAll(factoryUtils.initCustomers());
    }


}
