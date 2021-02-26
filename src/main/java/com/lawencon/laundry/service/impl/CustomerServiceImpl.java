package com.lawencon.laundry.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.CustomersDao;
import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.CustomerService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
@Transactional
public class CustomerServiceImpl extends BaseService implements CustomerService {

	@Autowired
	@Qualifier(value = "cust_jpa")
	private CustomersDao customersDao;

	@Override
	public void addCustomer(Customers customer) throws Exception {
		validateInput(customer, "add");
		customersDao.addCustomer(customer);
	}

	private void validateInput(Customers customer, String xx) throws Exception {
		StringBuilder vldMsg = new StringBuilder("Invalid input ");
		int msgLength = vldMsg.length();

		if (customer.getCustomerCode() == null || customer.getCustomerCode().trim().equals("")) {
			vldMsg.append(", customer code ");
		}

		if (customer.getFullName() == null || customer.getFullName().trim().equals("")) {
			vldMsg.append(", full name ");
		}

		if (customer.getPhone() == null || customer.getPhone().length() > 15 || customer.getPhone().trim().equals("")) {
			vldMsg.append(", phone ");
		}

		if (customer.getAddress() == null || customer.getAddress().trim().equals("")) {
			vldMsg.append(", address");
		}

		if (xx.equals("add")) {
			if (customer.getId() != null) {
				vldMsg.append(", id must null ");
			}
		} else if (xx.equals("update")) {
			if (customer.getId() == null) {
				vldMsg.append(", id must not null ");
			}
		}

		if (vldMsg.length() > msgLength) {
			throw new Exception(vldMsg.toString());
		}

	}

	@Override
	public Long getCountDataCustomers() throws Exception {
		return customersDao.getCountDataCustomers();

	}

	@Override
	public List<Customers> getListCustomers() throws Exception {
		List<Customers> listCustomers = customersDao.getListCustomers();

		if (listCustomers.isEmpty()) {
			throw new Exception("Data ini null!");
		} else {
			return customersDao.getListCustomers();
		}

	}

	@Override
	public Customers getByCode(Customers cust) throws Exception {
		return customersDao.getByCode(cust);
	}

	@Override
	public void deleteData(Customers cust) throws Exception {

		Customers customer = customersDao.getByCode(cust);
		if (customer == null) {
			throw new Exception("Customer code not match!");
		}
		Long id = customer.getId();

		Customers checkConstraint = customersDao.checkConstraint(id);
		if (checkConstraint == null) {
			customersDao.deleteData(customer.getCustomerCode());
		} else {
			throw new Exception("Can't delete data, constraint with another table!");
		}

	}

	@Override
	public void updateData(Customers customer) throws Exception {
		validateInput(customer, "update");

		Customers cust = customersDao.getById(customer);
		if (cust == null) {
			throw new Exception("Id not found!");
		}

		customersDao.updateData(customer);

	}

}
