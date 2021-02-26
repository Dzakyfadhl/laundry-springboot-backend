package com.lawencon.laundry.dao.jpanative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.CustomersDao;
import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.repo.CustomersRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "cust_jpa")
public class CustomersDaoJpaNativeImpl extends BaseDao implements CustomersDao {

	@Autowired
	private CustomersRepo customersRepo;

	@Override
	public void addCustomer(Customers customer) throws Exception {
		customersRepo.save(customer);
	}

	@Override
	public List<Customers> getListCustomers() throws Exception {
		return customersRepo.getListCustomers();
	}

	@Override
	public Long getCountDataCustomers() throws Exception {
		return customersRepo.getCountDataCustomers();
	}

	@Override
	public Customers getByCode(Customers customer) throws Exception {
		return customersRepo.getByCode(customer.getCustomerCode());
	}

	@Override
	public void deleteData(String code) throws Exception {
		customersRepo.deleteData(code);
	}

	@Override
	public Customers checkConstraint(Long id) throws Exception {
		Customers cust = customersRepo.cekConstraint(id);
		return cust == null ? null : cust;
	}

	@Override
	public void updateData(Customers customer) throws Exception {
		customersRepo.updateData(customer.getCustomerCode(), customer.getFullName(), customer.getPhone(),
				customer.getAddress(), customer.getId());

	}

	@Override
	public Customers getById(Customers customer) throws Exception {
		return customersRepo.getById(customer.getId());
	}

}
