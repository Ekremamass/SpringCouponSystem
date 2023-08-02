package com.ekrema.spring.coupon.system.controllers;

import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.security.LoginResponse;
import com.ekrema.spring.coupon.system.security.User;
import com.ekrema.spring.coupon.system.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("register/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void RegisterCompany(@RequestBody Company company) throws CouponSystemException {
        authService.registerCompany(company);
    }

    @PostMapping("register/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void RegisterCustomer(@RequestBody Customer customer) throws CouponSystemException {
        authService.registerCustomer(customer);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse login(@RequestBody User user) throws CouponSystemException {
        return authService.login(user);
    }
}
