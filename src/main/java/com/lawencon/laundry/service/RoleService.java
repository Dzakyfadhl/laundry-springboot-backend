package com.lawencon.laundry.service;

import com.lawencon.laundry.model.Roles;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface RoleService {

	Roles getIdByCode(String roleCode) throws Exception;

}
