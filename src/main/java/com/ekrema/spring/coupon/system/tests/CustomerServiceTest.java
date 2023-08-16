package com.ekrema.spring.coupon.system.tests;

import com.ekrema.spring.coupon.system.Utils.Art;
import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.login.ClientType;
import com.ekrema.spring.coupon.system.login.LoginManager;
import com.ekrema.spring.coupon.system.repos.CouponRepository;
import com.ekrema.spring.coupon.system.repos.CustomerRepository;
import com.ekrema.spring.coupon.system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceTest {
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;


    public void testAsCustomer() throws CouponSystemException {
        System.out.println(Art.CUSTOMER_SERVICE);

        Test.test("Customer Service - bad login");
        try {
            customerService = (CustomerService) loginManager.login("stam@email.com", "1234", ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - good login");
        try {
            customerService = (CustomerService) loginManager.login("johndoe@email.com", "1234", ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        int customerId = customerRepository.getIdByEmail("johndoe@email.com");

        Test.test("Customer Service - good purchase coupon id=1");
        Coupon toPurchase = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS));
        try {
            customerService.purchaseCoupon(customerId, toPurchase.getId());
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - bad purchase - already purchased");
        try {
            customerService.purchaseCoupon(customerId, toPurchase.getId());
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Service - get customer coupons");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);

        Test.test("Customer Service - get customer coupons category=Electricity");
        customerService.getCustomerCoupons(customerId, Category.ELECTRICITY).forEach(System.out::println);

        Test.test("Customer Service - get customer coupons max_price=3000");
        customerService.getCustomerCoupons(customerId, 3000).forEach(System.out::println);

        Test.test("Customer Service - get customer details");
        System.out.println(customerService.getCustomerDetails(customerId));
    }
}
