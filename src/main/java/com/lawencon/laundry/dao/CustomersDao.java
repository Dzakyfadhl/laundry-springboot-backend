package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Customers;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface CustomersDao {

	void addCustomer(Customers customer) throws Exception;

	List<Customers> getListCustomers() throws Exception;

	Long getCountDataCustomers() throws Exception;

	Customers getByCode(Customers customer) throws Exception;

	void deleteData(String code) throws Exception;

	Customers checkConstraint(Long id) throws Exception;

	void updateData(Customers customer) throws Exception;

	Customers getById(Customers customer) throws Exception;
}
