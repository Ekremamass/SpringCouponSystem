package com.ekrema.spring.coupon.system.exceptions;

public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getMessage());
    }
}
