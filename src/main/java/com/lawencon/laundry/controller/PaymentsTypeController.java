package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.helper.CustomResponse;
import com.lawencon.laundry.model.PaymentsType;
import com.lawencon.laundry.service.PaymentTypeService;

/**
 * @author Dzaky Fadhilla Guci
 */

@RestController
@RequestMapping("/payment")
public class PaymentsTypeController {

	@Autowired
	private PaymentTypeService paymentTypeService;

	@GetMapping
	public CustomResponse<?> getAll() {
		try {
			List<PaymentsType> listResult = paymentTypeService.getListPayments();
			return new CustomResponse<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse<>(null, HttpStatus.NOT_FOUND, "Data null");
		}

	}

	@PostMapping
	public CustomResponse<?> insert(@RequestBody String data) {
		try {
			PaymentsType paymentType = new ObjectMapper().readValue(data, PaymentsType.class);
			paymentTypeService.addPayment(paymentType);
			return new CustomResponse<>(paymentType, HttpStatus.CREATED);
		} catch (Exception e) {
			if (e.getMessage().contains("Invalid input")) {
				return new CustomResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(null, HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}

	@DeleteMapping
	public CustomResponse<?> delete(@RequestBody String data) {
		try {
			PaymentsType PaymentsType = new ObjectMapper().readValue(data, PaymentsType.class);
			paymentTypeService.deleteData(PaymentsType);
			return new CustomResponse<>(HttpStatus.OK, "Delete data success!");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals("Can't delete data, constraint with another table!")) {
				return new CustomResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(null, HttpStatus.BAD_REQUEST, "Delete data Failed");
			}

		}
	}

	@PutMapping
	public CustomResponse<?> update(@RequestBody String data) {
		try {
			PaymentsType paymentType = new ObjectMapper().readValue(data, PaymentsType.class);
			paymentTypeService.updateData(paymentType);
			return new CustomResponse<>(paymentType, HttpStatus.CREATED);
		} catch (Exception e) {
			if (e.getMessage().contains("Input invalid")) {
				return new CustomResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(null, HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}
}
