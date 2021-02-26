package com.lawencon.laundry.dao.hnative;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.TransactionsDao;
import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.model.Transactions;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "transaction_hib")
public class TransactionsDaoHibernateNativeImpl extends BaseDao implements TransactionsDao {

	@Override
	public Transactions addTransactions(Transactions transactions) throws Exception {

		String sql = bBuilder(
				"INSERT INTO tb_r_hdr_laundry (laundry_code, laundry_time, employee_id, payments_id, customer_id) values ",
				" (?1,?2, (SELECT id from tb_m_profiles WHERE employee_code = ?3 ), ",
				" (SELECT id from tb_m_payments_type WHERE payment_code = ?4), ",
				"(SELECT id from tb_m_customers WHERE customer_code =?5)) returning id ").toString();

		List<?> listTransaction = em.createNativeQuery(sql).setParameter(1, transactions.getLaundryCode())
				.setParameter(2, transactions.getLaundryTime())
				.setParameter(3, transactions.getEmployeeId().getEmployeeCode())
				.setParameter(4, transactions.getPaymentId().getPaymentCode())
				.setParameter(5, transactions.getCustomerId().getCustomerCode()).getResultList();

		Long id = listTransaction.size() > 0 ? Long.valueOf(listTransaction.get(0).toString()) : null;

		transactions.setId(id);

		return transactions;

	}

	@Override
	public Transactions updateTotalPrice(Transactions transactions) throws Exception {
		String sql = bBuilder("UPDATE tb_r_hdr_laundry ",
				"SET total_price = (SELECT SUM(price) FROM tb_r_dtl_laundry where hdr_id = ? group by hdr_id) ",
				"WHERE id = ? returning total_price").toString();

		BigDecimal totalPrice = (BigDecimal) em.createNativeQuery(sql).setParameter(1, transactions.getId())
				.setParameter(2, transactions.getId()).getSingleResult();

		transactions.setTotalPrice(totalPrice);
		return transactions;
	}

	@Override
	public List<Transactions> getAllTransactions() throws Exception {
		List<Transactions> listResult = new ArrayList<>();

		String sql = bBuilder(
				"SELECT hdr.laundry_code, hdr.total_price, hdr.laundry_time , c.customer_code, c.fullname, c.phone, c.address ",
				"FROM tb_r_hdr_laundry hdr INNER JOIN tb_m_customers c on c.id = hdr.customer_id ").toString();

		List<?> listObj = em.createNativeQuery(sql).getResultList();

		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;

			Transactions trs = new Transactions();
			trs.setLaundryCode((String) objArr[0]);
			trs.setTotalPrice((BigDecimal) objArr[1]);

			Timestamp inDate = (Timestamp) objArr[2];
			trs.setLaundryTime((LocalDateTime) inDate.toLocalDateTime());

			Customers c = new Customers();
			c.setCustomerCode((String) objArr[3]);
			c.setFullName((String) objArr[4]);
			c.setPhone((String) objArr[5]);
			c.setAddress((String) objArr[6]);
			trs.setCustomerId(c);

			listResult.add(trs);
		});

		return listResult;
	}

	@Override
	public Transactions getById(Transactions transactions) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateData(Transactions transactions) throws Exception {
		// TODO Auto-generated method stub

	}

}
