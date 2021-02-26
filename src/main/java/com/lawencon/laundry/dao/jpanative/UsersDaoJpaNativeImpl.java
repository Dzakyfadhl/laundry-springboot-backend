package com.lawencon.laundry.dao.jpanative;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.UsersDao;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.repo.UsersRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "user_jpa")
public class UsersDaoJpaNativeImpl extends BaseDao implements UsersDao {

	@Autowired
	private UsersRepo usersRepo;

	@Override
	public void addUser(Users user) throws Exception {
		usersRepo.save(user);
	}

	@Override
	public Long getCountData() throws Exception {
		return usersRepo.getCountData();
	}

	@Override
	public List<Users> getAllUsers() throws Exception {
		List<Users> listUsers = new ArrayList<Users>();
		List<?> listObj = usersRepo.getAllUsers();

		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users u = new Users();
			u.setId(Long.valueOf(objArr[0].toString()));
			u.setUsername((String) objArr[1]);

			Roles roles = new Roles();
			roles.setId(Long.valueOf(objArr[2].toString()));
			roles.setRoleCode((String) objArr[3]);

			u.setRolesId(roles);
			listUsers.add(u);

		});

		return listUsers;
	}

	@Override
	public Users findByUsername(String username) throws Exception {
		return usersRepo.findByUsername(username);
	}

	@Override
	public void updateData(Users user) throws Exception {
		usersRepo.updateData(user.getUsername(), user.getPassword());
	}

	@Override
	public Users getById(Users user) throws Exception {
		return usersRepo.getById(user.getId());
	}
}
