package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.PaymentsType;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface PaymentsTypeDao {

	void addPayment(PaymentsType paymentType) throws Exception;

	List<PaymentsType> getListPayments() throws Exception;

	Long getCountData() throws Exception;

	PaymentsType getByCode(PaymentsType pt) throws Exception;

	void deleteData(String code) throws Exception;

	PaymentsType checkConstraint(Long id) throws Exception;

	void updateData(PaymentsType paymentType) throws Exception;

	PaymentsType getById(PaymentsType paymentType) throws Exception;
}
