package com.lawencon.laundry.dao.jpanative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.RolesDao;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.repo.RolesRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "role_jpa")
public class RolesDaoJpaNativeImpl extends BaseDao implements RolesDao {

	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public Roles getIdByCode(String roleCode) throws Exception {
		return rolesRepo.getIdByCode(roleCode);
	}
}
