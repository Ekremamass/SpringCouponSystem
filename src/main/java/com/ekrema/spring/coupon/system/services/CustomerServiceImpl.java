package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerServiceImpl extends ClientService implements CustomerService{
    private int customerId;

    @Override
    public boolean login(String email, String password) {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            customerId = customerRepository.getIdByEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        if (couponRepository.existsPurchase(customerId, coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASED);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_ZERO);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        couponRepository.addCouponPurchase(customerId, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        return couponRepository.findByCustomer(customerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        return couponRepository.findByCustomerAndCategory(customerId,category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return couponRepository.findByCustomerAndMaxPrice(customerId,maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(()->new CouponSystemException(ErrMsg.CUSTOMER_NOT_EXISTS));
    }
}
