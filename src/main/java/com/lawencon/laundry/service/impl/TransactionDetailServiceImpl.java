package com.lawencon.laundry.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.TransactionDetailsDao;
import com.lawencon.laundry.model.Perfumes;
import com.lawencon.laundry.model.ServiceLaundry;
import com.lawencon.laundry.model.TransactionDetails;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.PerfumeService;
import com.lawencon.laundry.service.ServiceLaundryService;
import com.lawencon.laundry.service.TransactionDetailService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
public class TransactionDetailServiceImpl extends BaseService implements TransactionDetailService {

	private TransactionDetailsDao transactionDetailsDao;
	private PerfumeService perfumeService;
	private ServiceLaundryService serviceLaundryService;

	@Autowired
	public TransactionDetailServiceImpl(
			@Qualifier(value = "trxdetails_jpa") TransactionDetailsDao transactionDetailsDao,
			PerfumeService perfumeService, ServiceLaundryService serviceLaundryService) {
		this.transactionDetailsDao = transactionDetailsDao;
		this.perfumeService = perfumeService;
		this.serviceLaundryService = serviceLaundryService;
	}

	@Override
	public void addTransactionDetails(TransactionDetails transactionDetails) throws Exception {

		validateInput(transactionDetails, "add");

		// Get all perfume and service laundry by Code
		ServiceLaundry serviceLaundry = serviceLaundryService.getByCode(transactionDetails.getServiceId());
		Perfumes perfume = perfumeService.getByCode(transactionDetails.getPerfumeId());

		// Insert all data entity perfume and serviceLaundry to transactionDetails
		transactionDetails.setPerfumeId(perfume);
		transactionDetails.setServiceId(serviceLaundry);

		// Set price (Unit * service price)
		BigDecimal price = new BigDecimal(transactionDetails.getUnit()).multiply(serviceLaundry.getServicePrice());
		transactionDetails.setPrice(price);

		LocalDateTime pickUp = LocalDateTime.now().plusDays(serviceLaundry.getDayDuration());
		transactionDetails.setPickupTime(pickUp);
		transactionDetails.setStatus("Received");

		transactionDetailsDao.addTransactionDetails(transactionDetails);
	}

	private void validateInput(TransactionDetails trx, String action) throws Exception {
		StringBuilder vldMsg = new StringBuilder("Invalid input ");
		int msgLength = vldMsg.length();

		if (trx.getDtlDesc() == null || trx.getDtlDesc().trim().equals("")) {
			vldMsg.append(", description ");
		}

		if (trx.getUnit() == null || trx.getUnit() <= 0) {
			vldMsg.append(", unit ");
		}

		if (trx.getHdrId() == null) {
			vldMsg.append(", transaction id ");
		}

		if (trx.getPerfumeId() == null) {
			vldMsg.append(", perfume id ");
		}

		if (trx.getServiceId() == null) {
			vldMsg.append(", service id ");
		}

		if (action.equals("update")) {
			if (trx.getId() == null) {
				vldMsg.append(", id must not empty ");
			}
		} else if (action.equals("add")) {
			if (trx.getId() != null) {
				vldMsg.append(", id must empty ");
			}
		}

		if (vldMsg.length() > msgLength) {
			throw new IllegalArgumentException(vldMsg.toString());
		}

	}

	@Override
	@Transactional
	public List<TransactionDetails> getListDetails() throws Exception {
		return transactionDetailsDao.getListDetails();

	}

	@Override
	@Transactional
	public Long getCountData() throws Exception {
		return transactionDetailsDao.getCountData();

	}

	@Override
	@Transactional
	public void updatePickUpTime(TransactionDetails transactionDetails) throws Exception {
		transactionDetailsDao.updatePickUpTime(transactionDetails);

	}

	@Override
	public void updateData(TransactionDetails trx) throws Exception {
		validateInput(trx, "update");

		// Get all perfume and service laundry by Code
		ServiceLaundry serviceLaundry = serviceLaundryService.getByCode(trx.getServiceId());
		Perfumes perfume = perfumeService.getByCode(trx.getPerfumeId());

		if (serviceLaundry == null) {
			throw new Exception("Service laundry not found!");
		}

		if (perfume == null) {
			throw new Exception("Perfume not found!");
		}

		// Insert all data entity perfume and serviceLaundry to transactionDetails
		trx.setPerfumeId(perfume);
		trx.setServiceId(serviceLaundry);

		// Set price (Unit * service price)
		BigDecimal price = new BigDecimal(trx.getUnit()).multiply(serviceLaundry.getServicePrice());
		trx.setPrice(price);

		transactionDetailsDao.updateData(trx);

		TransactionDetails trxDB = transactionDetailsDao.getById(trx);

	}

}
