package com.ekrema.spring.coupon.system.security;

import com.ekrema.spring.coupon.system.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String email;
    private UUID token;
    private ClientType clientType;
}
