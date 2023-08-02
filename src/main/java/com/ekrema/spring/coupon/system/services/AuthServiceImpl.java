package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.security.LoginResponse;
import com.ekrema.spring.coupon.system.security.TokenService;
import com.ekrema.spring.coupon.system.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @Override
    public LoginResponse login(User user) throws CouponSystemException {
        switch (user.getClientType()) {
            case ADMINISTRATOR:
                if (!((ClientService) adminService).login(user.getEmail(), user.getPassword())) {
                    throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
                }
                break;
            case COMPANY:
                if (!((ClientService) companyService).login(user.getEmail(), user.getPassword())) {
                    throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
                }
                break;
            case CUSTOMER:
                if (!((ClientService) customerService).login(user.getEmail(), user.getPassword())) {
                    throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
                }
                break;
        }
        LoginResponse loginResponse = LoginResponse.builder().email(user.getEmail()).token(tokenService.addToken(user)).clientType(user.getClientType()).build();
        return loginResponse;
    }

    @Override
    public void registerCompany(Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @Override
    public void registerCustomer(Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }
}
