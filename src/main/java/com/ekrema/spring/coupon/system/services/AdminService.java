package com.ekrema.spring.coupon.system.services;

import com.ekrema.spring.coupon.system.beans.Company;
import com.ekrema.spring.coupon.system.beans.Customer;
import com.ekrema.spring.coupon.system.exceptions.CouponSystemException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public interface AdminService {
    Company addCompany(Company company) throws CouponSystemException;

    void updateCompany(int id, Company company) throws CouponSystemException;

    void deleteCompany(int id) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getOneCompany(int id) throws CouponSystemException;

    Customer addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(int id, Customer customer) throws CouponSystemException;

    void deleteCustomer(int id) throws CouponSystemException;

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int id) throws CouponSystemException;
}
