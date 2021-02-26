package com.lawencon.laundry.dao.jpanative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.PaymentsTypeDao;
import com.lawencon.laundry.model.PaymentsType;
import com.lawencon.laundry.repo.PaymentsTypeRepo;

/**
 * @author Dzaky Fadhilla Guci
 */
@Repository(value = "payment_jpa")
public class PaymentsTypeDaoJpaNativeImpl extends BaseDao implements PaymentsTypeDao {

	@Autowired
	private PaymentsTypeRepo paymentsTypeRepo;

	@Override
	public void addPayment(PaymentsType paymentType) throws Exception {
		paymentsTypeRepo.save(paymentType);
	}

	@Override
	public List<PaymentsType> getListPayments() throws Exception {
		return paymentsTypeRepo.getListPayments();
	}

	@Override
	public Long getCountData() throws Exception {
		return paymentsTypeRepo.getCountData();
	}

	@Override
	public PaymentsType getByCode(PaymentsType pt) throws Exception {
		return paymentsTypeRepo.getByCode(pt.getPaymentCode());
	}

	@Override
	public PaymentsType checkConstraint(Long id) throws Exception {
		PaymentsType paymentsType = paymentsTypeRepo.checkConstraint(id);
		return paymentsType == null ? null : paymentsType;
	}

	@Override
	public void deleteData(String code) throws Exception {
		paymentsTypeRepo.deleteData(code);

	}

	@Override
	public PaymentsType getById(PaymentsType paymentType) throws Exception {
		return paymentsTypeRepo.getById(paymentType.getId());
	}

	@Override
	public void updateData(PaymentsType paymentType) throws Exception {
		paymentsTypeRepo.updateData(paymentType.getPaymentCode(), paymentType.getPaymentName(), paymentType.getId());
	}
}
