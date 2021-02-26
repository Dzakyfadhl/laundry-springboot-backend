package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.PaymentsType;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface PaymentTypeService {

	void addPayment(PaymentsType paymentType) throws Exception;

	List<PaymentsType> getListPayments() throws Exception;

	Long getCountData() throws Exception;

	PaymentsType getByCode(PaymentsType pt) throws Exception;

	void deleteData(PaymentsType pt) throws Exception;

	void updateData(PaymentsType paymentType) throws Exception;

}
