package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.laundry.helper.CustomResponse;
import com.lawencon.laundry.helper.Helper;
import com.lawencon.laundry.model.Transactions;
import com.lawencon.laundry.service.TransactionService;

/**
 * @author Dzaky Fadhilla Guci
 */

@RestController
@RequestMapping("/transaction")
public class TransactionsController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/all")
	public CustomResponse<?> getAll() {
		try {
			List<Transactions> listResult = transactionService.getAllTransactions();
			return new CustomResponse<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse<>(null, HttpStatus.NOT_FOUND, "Data null");
		}

	}

	@PostMapping
	public CustomResponse<?> insert(@RequestBody String data) {
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			Helper helper = obj.readValue(data, Helper.class);

			Transactions transaction = transactionService.addTransactions(helper.transactions,
					helper.listDtlTransactions);

			return new CustomResponse<>(transaction, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Invalid input")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("Invalid foreign key")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}

	@PatchMapping
	public CustomResponse<?> updateForeign(@RequestBody String data) {
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());

			Transactions transaction = obj.readValue(data, Transactions.class);
			transactionService.updateData(transaction);

			return new CustomResponse<>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			if (e.getMessage().contains("Invalid input")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("Invalid foreign key")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("Id not match")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}
}
