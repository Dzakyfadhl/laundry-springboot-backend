package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.TransactionDetails;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface TransactionDetailService {

	void addTransactionDetails(TransactionDetails transcationDetails) throws Exception;

	List<TransactionDetails> getListDetails() throws Exception;

	void updatePickUpTime(TransactionDetails transactionDetails) throws Exception;

	Long getCountData() throws Exception;

	void updateData(TransactionDetails trx) throws Exception;
}
