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
import com.lawencon.laundry.model.Customers;
import com.lawencon.laundry.service.CustomerService;

/**
 * @author Dzaky Fadhilla Guci
 */

@RestController
@RequestMapping("/customer")
public class CustomersController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public CustomResponse<List<Customers>> getAll() {
		try {
			List<Customers> listResult = customerService.getListCustomers();
			return new CustomResponse<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse<>(null, HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping
	public CustomResponse<?> insert(@RequestBody String data) {
		try {
			Customers customer = new ObjectMapper().readValue(data, Customers.class);
			customerService.addCustomer(customer);
			return new CustomResponse<>(customer, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Invalid input")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}

	@DeleteMapping
	public CustomResponse<?> delete(@RequestBody String data) {
		try {
			Customers customer = new ObjectMapper().readValue(data, Customers.class);
			customerService.deleteData(customer);
			return new CustomResponse<>(HttpStatus.OK, "Delete data success!");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("constraint")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("code")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Delete data Failed");
			}

		}
	}

	@PutMapping
	public CustomResponse<?> update(@RequestBody String data) {
		try {
			Customers customer = new ObjectMapper().readValue(data, Customers.class);
			customerService.updateData(customer);
			return new CustomResponse<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Invalid input")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("Id")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}
}
