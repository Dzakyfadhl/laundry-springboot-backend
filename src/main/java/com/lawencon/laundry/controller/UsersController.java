package com.lawencon.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.laundry.helper.CustomResponse;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.service.UserService;

/**
 * @author Dzaky Fadhilla Guci
 */

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public CustomResponse<?> getAll() {
		try {
			List<Users> listResult = userService.getAllUsers();
			return new CustomResponse<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse<>(null, HttpStatus.NOT_FOUND, "Data null");
		}

	}

	@PostMapping
	public CustomResponse<Users> insert(@RequestBody String body) {
		Users user;
		try {
			user = new ObjectMapper().readValue(body, Users.class);
			userService.addUser(user);
			return new CustomResponse<Users>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Invalid input")) {
				return new CustomResponse<Users>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("role")) {
				return new CustomResponse<Users>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e.getMessage().contains("Username")) {
				return new CustomResponse<Users>(HttpStatus.BAD_REQUEST, e.getMessage());
			} else {
				return new CustomResponse<Users>(HttpStatus.BAD_REQUEST, "Insert data failed!");
			}
		}
	}
}
