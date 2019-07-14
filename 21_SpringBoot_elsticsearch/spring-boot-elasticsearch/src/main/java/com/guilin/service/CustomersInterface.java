package com.guilin.service;

import com.guilin.model.Customer;

import java.util.List;

public interface CustomersInterface {

    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);


}
