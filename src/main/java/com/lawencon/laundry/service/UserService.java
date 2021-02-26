package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Users;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface UserService {

	void addUser(Users user) throws Exception;

	List<Users> getAllUsers() throws Exception;

	Users findByUsername(String username) throws Exception;

	void updateData(Users user) throws Exception;

}
