package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Transactions;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface TransactionsDao {

	Transactions addTransactions(Transactions transactions) throws Exception;

	Transactions updateTotalPrice(Transactions transactions) throws Exception;

	List<Transactions> getAllTransactions() throws Exception;

	void updateData(Transactions transactions) throws Exception;

	Transactions getById(Transactions transactions) throws Exception;
}
