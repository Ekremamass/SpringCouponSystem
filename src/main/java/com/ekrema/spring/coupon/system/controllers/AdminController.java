package com.ekrema.spring.coupon.system.controllers;

import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.login.ClientType;
import com.ekrema.spring.coupon.system.security.TokenService;
import com.ekrema.spring.coupon.system.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("company")
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestHeader(value = "Authorization") UUID token, @RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.addCompany(company);
    }

    @PutMapping("company/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id, @RequestBody Company company) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.updateCompany(id, company);
    }

    @DeleteMapping("company/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.deleteCompany(id);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping("company/{id}")
    public Company getOneCompany(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getOneCompany(id);

    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestHeader(value = "Authorization") UUID token, @RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.addCustomer(customer);
    }

    @PutMapping("customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id, @RequestBody Customer customer) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.updateCustomer(id, customer);
    }


    @DeleteMapping("customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        adminService.deleteCustomer(id);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("customer/{id}")
    public Customer getOneCustomer(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        return adminService.getOneCustomer(id);
    }
}

