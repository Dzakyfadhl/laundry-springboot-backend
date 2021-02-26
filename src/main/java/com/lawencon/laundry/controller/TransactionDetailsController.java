package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.laundry.helper.CustomResponse;
import com.lawencon.laundry.model.TransactionDetails;
import com.lawencon.laundry.service.TransactionDetailService;

/**
 * @author Dzaky Fadhilla Guci
 */

@RestController
@RequestMapping("/trxdetail")
public class TransactionDetailsController {

	@Autowired
	private TransactionDetailService transactionDetailService;

	@GetMapping("/all")
	public CustomResponse<?> getAll() {
		try {
			List<TransactionDetails> listResult = transactionDetailService.getListDetails();
			return new CustomResponse<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse<>(null, HttpStatus.NOT_FOUND, "Data null");
		}

	}

}
