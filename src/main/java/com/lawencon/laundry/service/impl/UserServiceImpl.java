package com.lawencon.laundry.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.UsersDao;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.RoleService;
import com.lawencon.laundry.service.UserService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	private UsersDao userDao;
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(@Qualifier(value = "user_jpa") UsersDao userDao, RoleService roleService) {
		this.userDao = userDao;
		this.roleService = roleService;
	}

	@Override
	public void addUser(Users user) throws Exception {
		validateInput(user);

		Users userDb = userDao.findByUsername(user.getUsername());
		if (userDb != null) {
			throw new Exception("Username already exist!! T_T ");
		}

		Roles role = roleService.getIdByCode(user.getRolesId().getRoleCode());
		if (role == null) {
			throw new Exception("Cannot find role!");
		}

		user.setRolesId(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.addUser(user);

	}

	private void validateInput(Users user) throws Exception {

		if (user.getId() != null || user.getUsername() == null || user.getUsername().trim().equals("")
				|| user.getPassword() == null || user.getPassword().trim().equals("") || user.getRolesId() == null
				|| user.getRolesId().getRoleCode().trim().equals("") || user.getRolesId() == null) {
			StringBuilder msg = new StringBuilder("Invalid input ");

			if (user.getUsername() == null || user.getUsername().trim().equals("")) {
				msg.append(", username ");

			}

			if (user.getPassword() == null || user.getPassword().trim().equals("")) {

				msg.append(", password ");
			}

			if (user.getRolesId() == null || user.getRolesId().getRoleCode() == null
					|| user.getRolesId().getRoleCode().trim().equals("") || user.getRolesId() == null) {
				msg.append(", role code ");
			}

			if (user.getId() != null) {
				msg.append(", id");

			}
			throw new Exception(msg.toString());

		}
	}

	@Override
	public List<Users> getAllUsers() throws Exception {
		return userDao.getAllUsers();

	}

	@Override
	public Users findByUsername(String username) throws Exception {
		return userDao.findByUsername(username);
	}

	@Override
	public void updateData(Users user) throws Exception {
		StringBuilder msg = new StringBuilder("Invalid input ");
		int msgLength = msg.length();

		if (user.getId() == null) {
			msg.append(", id must not null ");
		}

		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			msg.append(", username ");
		}

		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			msg.append(", password ");
		}

		if (msg.length() > msgLength) {
			throw new Exception(msg.toString());
		}

		Users userDb = userDao.getById(user);
		if (userDb == null) {
			throw new Exception("User not found! ");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRolesId(userDb.getRolesId());
		userDao.updateData(user);

	}
}
