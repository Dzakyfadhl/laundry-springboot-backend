package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Customers;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface CustomerService {

	void addCustomer(Customers customer) throws Exception;

	List<Customers> getListCustomers() throws Exception;

	Long getCountDataCustomers() throws Exception;

	Customers getByCode(Customers cust) throws Exception;

	void deleteData(Customers cust) throws Exception;

	void updateData(Customers customer) throws Exception;

}
