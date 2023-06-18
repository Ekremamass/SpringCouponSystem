package com.ekrema.spring.coupon.system.clr;

import com.ekrema.spring.coupon.system.Utils.Art;
import com.ekrema.spring.coupon.system.Utils.FactoryUtils;
import com.ekrema.spring.coupon.system.repos.CompanyRepository;
import com.ekrema.spring.coupon.system.repos.CouponRepository;
import com.ekrema.spring.coupon.system.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
        System.out.println(Art.COMPANIES);
        companyRepository.saveAll(factoryUtils.initCompanies());
        companyRepository.findAll().forEach(System.out::println);

        System.out.println(Art.COUPONS);
        couponRepository.saveAll(factoryUtils.initCoupons());
        couponRepository.findAll().forEach(System.out::println);

        System.out.println(Art.CUSTOMERS);
        customerRepository.saveAll(factoryUtils.initCustomers());
        customerRepository.findAll().forEach(System.out::println);

        couponRepository.addCouponPurchase(1,3);
        couponRepository.addCouponPurchase(1,2);
        couponRepository.addCouponPurchase(2,1);
    }


}
