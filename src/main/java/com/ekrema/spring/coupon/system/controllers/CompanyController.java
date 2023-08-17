package com.ekrema.spring.coupon.system.controllers;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.login.ClientType;
import com.ekrema.spring.coupon.system.security.TokenService;
import com.ekrema.spring.coupon.system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader(value = "Authorization") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("coupon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id, @RequestBody Coupon coupon) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        companyService.updateCoupon(companyId, id, coupon);
    }

    @DeleteMapping("coupon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int id) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        companyService.deleteCoupon(companyId, id);
    }

    @GetMapping("coupons")
    public List<Coupon> getCompanyCoupons(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyCoupons(companyId);
    }

    @GetMapping("coupons/category")
    public List<Coupon> getCompanyCoupons(@RequestHeader(value = "Authorization") UUID token, @RequestParam String category) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyCoupons(companyId, Category.valueOf(category.toUpperCase()));
    }

    @GetMapping("coupons/maxPrice")
    public List<Coupon> getCompanyCoupons(@RequestHeader(value = "Authorization") UUID token, @RequestParam double maxPrice) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

    @GetMapping("details")
    public Company getCompanyDetails(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED);
        }
        int companyId = tokenService.getId(token);
        return companyService.getCompanyDetails(companyId);
    }
}
