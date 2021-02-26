package com.lawencon.laundry.dao.jpanative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.TransactionsDao;
import com.lawencon.laundry.model.Transactions;
import com.lawencon.laundry.repo.TransactionsRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "transaction_jpa")
public class TransactionsDaoJpaNativeImpl extends BaseDao implements TransactionsDao {

	@Autowired
	private TransactionsRepo transactionsRepo;

	@Override
	public Transactions addTransactions(Transactions transactions) throws Exception {
		return transactionsRepo.save(transactions);
	}

	@Override
	public Transactions updateTotalPrice(Transactions transactions) throws Exception {
		transactionsRepo.updateTotalPrice(transactions.getId(), transactions.getId());
		return transactions;
	}

	@Override
	public List<Transactions> getAllTransactions() throws Exception {
		return transactionsRepo.getAllTransactionss();
//		List<Transactions> listResult = new ArrayList<>();
//
//		List<?> listObj = transactionsRepo.getAllTransactions();
//
//		listObj.forEach(val -> {
//			Object[] objArr = (Object[]) val;
//
//			Transactions trs = new Transactions();
//			trs.setLaundryCode((String) objArr[0]);
//			trs.setTotalPrice((BigDecimal) objArr[1]);
//
//			Timestamp inDate = (Timestamp) objArr[2];
//			trs.setLaundryTime((LocalDateTime) inDate.toLocalDateTime());
//
//			Customers c = new Customers();
//			c.setCustomerCode((String) objArr[3]);
//			c.setFullName((String) objArr[4]);
//			c.setPhone((String) objArr[5]);
//			c.setAddress((String) objArr[6]);
//			trs.setCustomerId(c);
//
//			listResult.add(trs);
//		});
//
//		return listResult;
	}

	@Override
	public Transactions getById(Transactions transactions) throws Exception {
		return transactionsRepo.getById(transactions.getId());
	}

	@Override
	public void updateData(Transactions transactions) throws Exception {
		transactionsRepo.updateData(transactions.getCustomerId().getId(), transactions.getEmployeeId().getId(),
				transactions.getPaymentId().getId(), transactions.getId());
	}
}
