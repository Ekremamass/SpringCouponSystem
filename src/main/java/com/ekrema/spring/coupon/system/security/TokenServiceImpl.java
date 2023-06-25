package com.ekrema.spring.coupon.system.security;

import com.ekrema.spring.coupon.system.beans.User;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import com.ekrema.spring.coupon.system.repos.CompanyRepository;
import com.ekrema.spring.coupon.system.repos.CustomerRepository;
import com.ekrema.spring.coupon.system.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private Map<UUID,Information> tokens = new HashMap<>();

    @Override
    public UUID addToken(User user) {
        UUID token = UUID.randomUUID();
        int id = 0;
        switch (user.getClientType()){
            case ADMINSTRATOR :
                break;
            case COMPANY:
                id = companyRepository.getIdByEmail(user.getEmail());
                break;
            case CUSTOMER:
                id = customerRepository.getIdByEmail(user.getEmail());
                break;
        }
        Information info = Information.builder()
                .id(id)
                .time(LocalDateTime.now())
                .clientType(user.getClientType())
                .build();
        tokens.put(token,info);
        return token;
    }
}
