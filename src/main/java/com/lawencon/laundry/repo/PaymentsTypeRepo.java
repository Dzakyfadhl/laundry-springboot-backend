package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.PaymentsType;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface PaymentsTypeRepo extends JpaRepository<PaymentsType, Long> {

	@Query(value = "SELECT * FROM tb_m_payments_type", nativeQuery = true)
	List<PaymentsType> getListPayments() throws Exception;

	@Query(value = "SELECT count(*) as total_data FROM tb_m_payments_type", nativeQuery = true)
	Long getCountData() throws Exception;

	@Query(value = "SELECT * FROM tb_m_payments_type WHERE payment_code = ?1 ", nativeQuery = true)
	PaymentsType getByCode(String code) throws Exception;

	@Query(value = "SELECT * FROM tb_m_payments_type WHERE id=?1 AND id in (SELECT payments_id FROM tb_r_hdr_shippings) LIMIT 1 ", nativeQuery = true)
	PaymentsType checkConstraint(Long id) throws Exception;

	@Modifying
	@Query(value = "DELETE FROM tb_m_payments_type WHERE payment_code = ?1", nativeQuery = true)
	void deleteData(String code) throws Exception;

	@Query(value = "SELECT * FROM tb_m_payments_type WHERE id = ?1 ", nativeQuery = true)
	PaymentsType getById(Long id) throws Exception;

	@Query(value = "UPDATE tb_m_payments_type SET payment_code = ?1 , payment_name = ?2  WHERE id = ?3", nativeQuery = true)
	void updateData(String code, String name, Long id) throws Exception;

}
