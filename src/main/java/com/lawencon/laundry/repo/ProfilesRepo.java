package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Profiles;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface ProfilesRepo extends JpaRepository<Profiles, Long> {

	@Query(value = "SELECT * FROM tb_m_profiles WHERE employee_code = ? ", nativeQuery = true)
	Profiles getByCode(String empCode) throws Exception;

	@Query(value = "SELECT p.employee_code, p.fullname, p.phone, p.address, u.username FROM tb_m_profiles p INNER JOIN tb_m_users u on u.id = p.users_id ", nativeQuery = true)
	List<?> getListProfiles() throws Exception;

	@Query(value = "SELECT * FROM tb_m_profiles WHERE users_id = ?", nativeQuery = true)
	Profiles getByUserId(Long userId) throws Exception;

	@Query(value = "SELECT * FROM tb_m_profiles WHERE id = ?1 AND id in (SELECT employee_id FROM tb_r_hdr_laundry) LIMIT 1 ", nativeQuery = true)
	Profiles checkConstraint(Long id) throws Exception;

	@Modifying
	@Query(value = "DELETE FROM tb_m_profiles WHERE employee_code = ?1", nativeQuery = true)
	void deleteData(String code) throws Exception;

	@Modifying
	@Query(value = "UPDATE tb_m_profiles SET employee_code = ?1 , fullname=?2 , phone=?3 , address=?4 WHERE id =?5  ", nativeQuery = true)
	void updateData(String code, String name, String phone, String address, Long id) throws Exception;

	@Query(value = "SELECT * FROM tb_m_profiles WHERE id = ?", nativeQuery = true)
	Profiles getById(Long userId) throws Exception;

}
