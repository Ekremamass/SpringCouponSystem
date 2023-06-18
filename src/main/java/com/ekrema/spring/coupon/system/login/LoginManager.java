package com.ekrema.spring.coupon.system.login;

import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.services.AdminService;
import com.ekrema.spring.coupon.system.services.AdminServiceImpl;
import com.ekrema.spring.coupon.system.services.ClientService;
import com.ekrema.spring.coupon.system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case ADMINSTRATOR:
                if (((ClientService) adminService).login(email,password)) {
                    return (ClientService) adminService;
                }
                break;
            case COMPANY:
                if (((ClientService) companyService).login(email,password)) {
                    return (ClientService) companyService;
                }
                break;
        }
        throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
    }


}
