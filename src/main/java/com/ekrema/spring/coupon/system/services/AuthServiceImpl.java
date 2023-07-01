package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.security.TokenService;
import com.ekrema.spring.coupon.system.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public UUID login(User user) throws CouponSystemException {
        switch (user.getClientType()) {
            case ADMINSTRATOR:
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
        return tokenService.addToken(user);
    }
}
