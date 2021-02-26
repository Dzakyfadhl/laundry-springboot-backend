package com.lawencon.laundry.dao;

import com.lawencon.laundry.model.Roles;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface RolesDao {

	Roles getIdByCode(String roleCode) throws Exception;

}
