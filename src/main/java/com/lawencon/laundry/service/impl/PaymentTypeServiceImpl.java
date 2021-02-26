package com.lawencon.laundry.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.PaymentsTypeDao;
import com.lawencon.laundry.model.PaymentsType;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.PaymentTypeService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
@Transactional
public class PaymentTypeServiceImpl extends BaseService implements PaymentTypeService {

	@Autowired
	@Qualifier(value = "payment_jpa")
	private PaymentsTypeDao paymentsTypeDao;

	@Override
	public void addPayment(PaymentsType paymentType) throws Exception {
		validateInput(paymentType);
		paymentsTypeDao.addPayment(paymentType);

	}

	private void validateInput(PaymentsType pt) throws Exception {
		StringBuilder vldMsg = new StringBuilder("Invalid input ");
		int msgLength = vldMsg.length();

		if (pt.getPaymentCode() == null || pt.getPaymentCode().trim().equals("")) {
			vldMsg.append(", payment code ");
		}

		if (pt.getPaymentName() == null || pt.getPaymentName().trim().equals("")) {
			vldMsg.append(", payment name ");
		}

		if (pt.getId() != null) {
			vldMsg.append(", id ");
		}

		if (vldMsg.length() > msgLength) {
			throw new Exception(vldMsg.toString());
		}

	}

	@Override
	public List<PaymentsType> getListPayments() throws Exception {
		return paymentsTypeDao.getListPayments();

	}

	@Override
	public Long getCountData() throws Exception {
		return paymentsTypeDao.getCountData();

	}

	@Override
	public PaymentsType getByCode(PaymentsType pt) throws Exception {
		return paymentsTypeDao.getByCode(pt);
	}

	@Override
	public void deleteData(PaymentsType pt) throws Exception {
		PaymentsType paymentsType = paymentsTypeDao.getByCode(pt);
		Long id = paymentsType.getId();

		PaymentsType checkConstraint = paymentsTypeDao.checkConstraint(id);
		if (checkConstraint == null) {
			paymentsTypeDao.deleteData(paymentsType.getPaymentCode());
		} else {
			throw new Exception("Can't delete data, constraint with another table!");
		}

	}

	@Override
	public void updateData(PaymentsType paymentType) throws Exception {

		if (paymentType.getId() == null) {
			throw new Exception("Invalid input , id must not null! ");
		}

		PaymentsType ptDB = paymentsTypeDao.getById(paymentType);
		if (ptDB == null) {
			throw new Exception("Invalid input , id not found! ");
		}

		if (paymentType.getPaymentCode() == null) {
			paymentType.setPaymentCode(ptDB.getPaymentCode());
		}

		if (paymentType.getPaymentName() == null) {
			paymentType.setPaymentName(ptDB.getPaymentName());
		}

		paymentsTypeDao.updateData(paymentType);
	}

}
