package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Coupon;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import com.ekrema.spring.coupon.system.exceptions.ErrMsg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class AdminServiceImpl extends ClientService implements AdminService {
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";

    @Override
    public boolean login(String email, String password) {
        return email.equals(EMAIL) && password.equals(PASSWORD);
    }

    @Override
    public Company addCompany(Company company) throws CouponSystemException {
        int id = company.getId();
        if (companyRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_EXISTS);
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXISTS);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXISTS);
        }
        return companyRepository.save(company);
    }

    @Override
    public void updateCompany(int id, Company company) throws CouponSystemException {
        if (!companyRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST);
        }
        if (id != company.getId()) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_MATCH);
        }
        if (!companyRepository.findById(id).get().getName().equals(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_NOT_MATCH);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int id) throws CouponSystemException {
        if (!companyRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST);
        }
        List<Coupon> coupons = couponRepository.findByCompany_id(id);
        for (Coupon coupon : coupons) {
            int coupon_id = coupon.getId();
            couponRepository.deletePurchaseByCouponId(coupon_id);
            couponRepository.deleteById(coupon_id);
        }

        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int id) throws CouponSystemException {
        return companyRepository.findById(id).orElseThrow(() -> new CouponSystemException(ErrMsg.COMPANY_NOT_EXIST));
    }

    @Override
    public Customer addCustomer(Customer customer) throws CouponSystemException {
        int id = customer.getId();
        if (customerRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_EXISTS);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_EXISTS);
        }
        return customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int id, Customer customer) throws CouponSystemException {
        if (!customerRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_EXISTS);
        }
        if (id != customer.getId()) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_MATCH);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int id) throws CouponSystemException {
        if (!customerRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_EXISTS);
        }
        customerRepository.deletePurchaseByCustomerId(id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int id) throws CouponSystemException {
        if (!customerRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_EXISTS);
        }
        return customerRepository.findById(id).get();
    }

}
