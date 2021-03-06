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
import com.lawencon.laundry.model.Perfumes;
import com.lawencon.laundry.service.PerfumeService;

/**
 * @author Dzaky Fadhilla Guci
 */

@RestController
@RequestMapping("/perfume")
public class PerfumesController {

	@Autowired
	private PerfumeService perfumeService;

	@GetMapping
	public CustomResponse<?> getAll() {
		try {
			List<Perfumes> listResult = perfumeService.getListPerfumes();
			return new CustomResponse<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse<>(null, HttpStatus.NOT_FOUND, "Data null");
		}

	}

	@PostMapping
	public CustomResponse<?> insert(@RequestBody String data) {
		try {
			Perfumes perfume = new ObjectMapper().readValue(data, Perfumes.class);
			perfumeService.addPerfume(perfume);
			return new CustomResponse<>(perfume, HttpStatus.CREATED);
		} catch (Exception e) {
			if (e.getMessage().equals("Input invalid")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}

	@DeleteMapping
	public CustomResponse<?> delete(@RequestBody String data) {
		try {
			Perfumes perfumes = new ObjectMapper().readValue(data, Perfumes.class);
			perfumeService.deleteData(perfumes);
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
			Perfumes perfume = new ObjectMapper().readValue(data, Perfumes.class);
			perfumeService.updateData(perfume);
			return new CustomResponse<>(perfume, HttpStatus.OK);
		} catch (Exception e) {
			if (e.getMessage().equals("Input invalid")) {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<>(HttpStatus.BAD_REQUEST, "Insert Data Failed");
			}

		}
	}

}
