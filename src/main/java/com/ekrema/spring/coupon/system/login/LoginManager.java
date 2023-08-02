package com.ekrema.spring.coupon.system.login;

import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.services.AdminService;
import com.ekrema.spring.coupon.system.services.ClientService;
import com.ekrema.spring.coupon.system.services.CompanyService;
import com.ekrema.spring.coupon.system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case ADMINISTRATOR:
                if (((ClientService) adminService).login(email, password)) {
                    return (ClientService) adminService;
                }
                break;
            case COMPANY:
                if (((ClientService) companyService).login(email, password)) {
                    return (ClientService) companyService;
                }
                break;
            case CUSTOMER:
                if (((ClientService) customerService).login(email, password)) {
                    return (ClientService) customerService;
                }
                break;
        }
        throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
    }


}
