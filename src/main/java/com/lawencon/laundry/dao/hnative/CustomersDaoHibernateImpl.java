package com.lawencon.laundry.dao.hnative;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.CustomersDao;
import com.lawencon.laundry.model.Customers;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "cust_hib")
public class CustomersDaoHibernateImpl extends BaseDao implements CustomersDao {

	@Override
	public void addCustomer(Customers customer) throws Exception {

		String sql = bBuilder("INSERT INTO tb_m_customers (customer_code, fullname, phone, address) values ",
				" (?1,?2,?3,?4) ").toString();

		em.createNativeQuery(sql).setParameter(1, customer.getCustomerCode()).setParameter(2, customer.getFullName())
				.setParameter(3, customer.getPhone()).setParameter(4, customer.getAddress()).executeUpdate();

	}

	@Override
	public List<Customers> getListCustomers() throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_customers").toString();
		List<Customers> listObj = em.createNativeQuery(sql).getResultList();

		List<Customers> listResult = new ArrayList<>();

		return listObj;
	}

	@Override
	public Long getCountDataCustomers() throws Exception {
		String sql = bBuilder("SELECT count(*) as total_data FROM tb_m_customers").toString();
		return (Long) em.createNativeQuery(sql).getSingleResult();

	}

	@Override
	public Customers getByCode(Customers customer) throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_customers WHERE customer_code = ?1 ").toString();
		Object result = em.createNativeQuery(sql).setParameter(1, customer.getCustomerCode()).getResultList().get(0);

		Customers resultc = (Customers) result;

		return resultc;
	}

	@Override
	public void deleteData(String code) throws Exception {
		String sql = "DELETE FROM tb_m_customers WHERE customer_code = ?1";
		em.createNativeQuery(sql).setParameter(1, code).executeUpdate();
	}

	@Override
	public Customers checkConstraint(Long id) throws Exception {
		String sql = "SELECT * FROM tb_m_customers WHERE id=?1 AND id in (SELECT customer_id FROM tb_r_hdr_shippings) LIMIT 1";
		Object result = em.createNativeQuery(sql).setParameter(1, id).getResultList();

		Customers resultc = (Customers) result;

		return resultc;

	}

	@Override
	public void updateData(Customers customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Customers getById(Customers customer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
