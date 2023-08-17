package com.ekrema.spring.coupon.system.controllers;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.login.ClientType;
import com.ekrema.spring.coupon.system.security.TokenService;
import com.ekrema.spring.coupon.system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("purchase/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        customerService.purchaseCoupon(customerId, id);
    }

    @GetMapping("coupons")
    public List<Coupon> getCustomerCoupons(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerCoupons(customerId);
    }


    @GetMapping("coupons/category")
    public List<Coupon> getCustomerCoupons(@RequestHeader(value = "Authorization") UUID token, @RequestParam Category category) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerCoupons(customerId, category);
    }

    @GetMapping("coupons/maxPrice")
    public List<Coupon> getCustomerCoupons(@RequestHeader(value = "Authorization") UUID token, @RequestParam double maxPrice) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerCoupons(customerId, maxPrice);
    }

    @GetMapping("details")
    public Customer getCustomerDetails(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int customerId = tokenService.getId(token);
        return customerService.getCustomerDetails(customerId);
    }

    @GetMapping("all")
    public List<Coupon> getAllCoupons(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return customerService.getAllCoupons();
    }

}
