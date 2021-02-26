package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Users;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface UsersDao {

	void addUser(Users user) throws Exception;

	Long getCountData() throws Exception;

	List<Users> getAllUsers() throws Exception;

	Users findByUsername(String username) throws Exception;

	void updateData(Users user) throws Exception;

	Users getById(Users user) throws Exception;
}
