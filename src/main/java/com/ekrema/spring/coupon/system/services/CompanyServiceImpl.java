package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Category;
import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService{
    private int companyId;
    @Override
    public boolean login(String email, String password) {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            companyId = companyRepository.getIdByEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (couponRepository.existsByCompany_idAndTitle(companyId, coupon.getTitle())) {
            throw new CouponSystemException(ErrMsg.COUPON_TITLE_EXISTS);
        }
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int id, Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        Coupon original = couponRepository.findById(id).get();
        if (original.getId() != coupon.getId()) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_MATCH);
        }
        if (original.getCompany().equals(coupon.getCompany())) {
            throw new CouponSystemException(ErrMsg.COUPON_COMPANY_NOT_MATCH);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int id) throws CouponSystemException {
        if (!couponRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        if (couponRepository.findById(id).get().getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        couponRepository.deleteById(id);
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findByCompany_id(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepository.findByCompany_idAndCategory(companyId,category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepository.findByCompany_idAndPriceLessThanEqual(companyId,maxPrice);
    }

    @Override
    public Company getCompanyDetails() throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(()-> new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST));
    }
}
