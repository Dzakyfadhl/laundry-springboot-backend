package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Users;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

	@Query(value = "SELECT * FROM tb_m_users WHERE username = ?1", nativeQuery = true)
	Users findByUsername(String username) throws Exception;

	@Query(value = "SELECT * FROM tb_m_users WHERE username = ?1", nativeQuery = true)
	Users login(Users user) throws Exception;

	@Query(value = "SELECT count(*) as total_data FROM tb_m_users", nativeQuery = true)
	Long getCountData() throws Exception;

	@Query(value = "SELECT u.id, u.username, r.id as id_roles, r.role_code  FROM tb_m_users u INNER JOIN tb_m_roles r", nativeQuery = true)
	List<Object[]> getAllUsers() throws Exception;

	@Modifying
	@Query(value = "UPDATE tb_m_users SET username=?1 , passwords=?2", nativeQuery = true)
	void updateData(String username, String password) throws Exception;

	@Query(value = "SELECT * FROM tb_m_users WHERE id = ?1", nativeQuery = true)
	Users getById(Long id) throws Exception;

}
