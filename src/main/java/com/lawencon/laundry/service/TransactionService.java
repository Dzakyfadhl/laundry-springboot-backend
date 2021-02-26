package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.TransactionDetails;
import com.lawencon.laundry.model.Transactions;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface TransactionService {

	Transactions addTransactions(Transactions transactions, List<TransactionDetails> listDtlTransactions)
			throws Exception;

	List<Transactions> getAllTransactions() throws Exception;

	void updateData(Transactions transactions) throws Exception;

}
