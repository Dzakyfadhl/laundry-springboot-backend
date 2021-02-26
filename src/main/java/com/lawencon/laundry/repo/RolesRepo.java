package com.lawencon.laundry.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Roles;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {

	@Query(value = "SELECT * FROM tb_m_roles WHERE role_code = ?1", nativeQuery = true)
	Roles getIdByCode(String roleCode) throws Exception;

}
