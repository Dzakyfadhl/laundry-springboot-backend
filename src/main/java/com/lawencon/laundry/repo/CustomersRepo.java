package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Customers;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface CustomersRepo extends JpaRepository<Customers, Long> {

	@Query(value = "SELECT * FROM tb_m_customers", nativeQuery = true)
	List<Customers> getListCustomers() throws Exception;

	@Query(value = "SELECT count(*) as total_data FROM tb_m_customers", nativeQuery = true)
	Long getCountDataCustomers() throws Exception;

	@Query(value = "SELECT * FROM tb_m_customers WHERE customer_code = ?1", nativeQuery = true)
	Customers getByCode(String code) throws Exception;

	@Modifying
	@Query(value = "DELETE FROM tb_m_customers WHERE customer_code = ?1", nativeQuery = true)
	void deleteData(String code) throws Exception;

	@Query(value = "SELECT * FROM tb_m_customers WHERE id=?1 AND id in (SELECT customer_id FROM tb_r_hdr_laundry) LIMIT 1", nativeQuery = true)
	Customers cekConstraint(Long id) throws Exception;

	@Modifying
	@Query(value = "UPDATE tb_m_customers SET customer_code = ?1 , fullname = ?2 , phone=?3 , address = ?4  WHERE id = ?5", nativeQuery = true)
	void updateData(String code, String fullname, String phone, String address, Long id) throws Exception;

	@Query(value = "SELECT * FROM tb_m_customers WHERE id = ?1", nativeQuery = true)
	Customers getById(Long id) throws Exception;
}
