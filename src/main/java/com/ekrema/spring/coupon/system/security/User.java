package com.ekrema.spring.coupon.system.security;

import com.ekrema.spring.coupon.system.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String email;
    private String password;
    private ClientType clientType;
}
