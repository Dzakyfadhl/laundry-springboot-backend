package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.TransactionDetails;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface TransactionDetailsDao {

	void addTransactionDetails(TransactionDetails transcationDetails) throws Exception;

	List<TransactionDetails> getListDetails() throws Exception;

	void updatePickUpTime(TransactionDetails transactionDetails) throws Exception;

	Long getCountData() throws Exception;

	void updateData(TransactionDetails trx) throws Exception;

	TransactionDetails getById(TransactionDetails trx) throws Exception;
}
