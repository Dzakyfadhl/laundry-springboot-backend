package com.lawencon.laundry.dao.jpanative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.TransactionDetailsDao;
import com.lawencon.laundry.model.TransactionDetails;
import com.lawencon.laundry.repo.TransactionDetailsRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "trxdetails_jpa")
public class TransactionDetailsDaoJpaNativeImpl extends BaseDao implements TransactionDetailsDao {

	@Autowired
	private TransactionDetailsRepo transactionDetailsRepo;

	@Override
	public void addTransactionDetails(TransactionDetails transactionDetails) throws Exception {
		transactionDetailsRepo.save(transactionDetails);
	}

	@Override
	public List<TransactionDetails> getListDetails() throws Exception {
		return transactionDetailsRepo.getListDetails();
	}

	@Override
	public void updatePickUpTime(TransactionDetails transactionDetails) throws Exception {
		transactionDetailsRepo.updatePickUpTime(transactionDetails.getDtlCode());
	}

	@Override
	public Long getCountData() throws Exception {
		return transactionDetailsRepo.getCountData();
	}

	@Override
	public TransactionDetails getById(TransactionDetails trx) throws Exception {
		return transactionDetailsRepo.getById(trx.getId());
	}

	@Override
	public void updateData(TransactionDetails trx) throws Exception {
		transactionDetailsRepo.updateData(trx.getDtlDesc(), trx.getUnit(), trx.getPerfumeId().getId(),
				trx.getServiceId().getId(), trx.getId());
	}
}
