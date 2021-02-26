package com.lawencon.laundry.dao.hnative;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.PaymentsTypeDao;
import com.lawencon.laundry.model.PaymentsType;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "payment_hib")
public class PaymentsTypeDaoHibernateImpl extends BaseDao implements PaymentsTypeDao {

	@Override
	public void addPayment(PaymentsType paymentType) throws Exception {
		String sql = bBuilder("INSERT INTO tb_m_payments_type (payment_code, payment_name) values (?,?) ").toString();

		em.createNativeQuery(sql).setParameter(1, paymentType.getPaymentCode())
				.setParameter(2, paymentType.getPaymentName()).executeUpdate();

	}

	@Override
	public List<PaymentsType> getListPayments() throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_payments_type").toString();
		List<PaymentsType> listResult = em.createNativeQuery(sql, PaymentsType.class).getResultList();
		return listResult;
	}

	@Override
	public Long getCountData() throws Exception {
		String sql = bBuilder("SELECT count(*) as total_data FROM tb_m_payments_type").toString();
		return Long.valueOf(em.createNativeQuery(sql).getSingleResult().toString());
	}

	@Override
	public PaymentsType getByCode(PaymentsType pt) throws Exception {
		String sql = "SELECT * FROM tb_m_payments_type WHERE payment_code = ?1 ";
		Object result = em.createNativeQuery(sql).setParameter(1, pt.getPaymentCode()).getResultList().get(0);

		PaymentsType resultc = (PaymentsType) result;

		return resultc;
	}

	@Override
	public PaymentsType checkConstraint(Long id) throws Exception {
		String sql = "SELECT * FROM tb_m_payments_type WHERE id=?1 AND id in (SELECT payments_id FROM tb_r_hdr_shippings) LIMIT 1";
		Object result = em.createNativeQuery(sql).setParameter(1, id).getResultList().get(0);

		PaymentsType resultc = (PaymentsType) result;

		return resultc;
	}

	@Override
	public void deleteData(String code) throws Exception {
		String sql = "DELETE FROM tb_m_payments_type WHERE payment_code = ?1";
		em.createNativeQuery(sql).setParameter(1, code).executeUpdate();
	}

	@Override
	public PaymentsType getById(PaymentsType paymentType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateData(PaymentsType paymentType) throws Exception {
		// TODO Auto-generated method stub

	}
}
