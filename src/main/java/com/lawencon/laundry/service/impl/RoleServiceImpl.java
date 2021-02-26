package com.lawencon.laundry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.RolesDao;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.RoleService;

/**
 * @author Dzaky Fadhilla Guci
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {

	@Autowired
	@Qualifier(value = "role_jpa")
	private RolesDao rolesDao;

	@Override
	public Roles getIdByCode(String roleCode) throws Exception {
		return rolesDao.getIdByCode(roleCode);
	}

}
