package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.TransactionDetails;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface TransactionDetailsRepo extends JpaRepository<TransactionDetails, Long> {

	@Query(value = "SELECT * FROM tb_r_dtl_laundry dtl INNER JOIN tb_r_hdr_laundry hdr ON hdr.id = dtl.hdr_id INNER JOIN tb_m_perfumes tmp ON tmp.id_perfume = dtl.perfume_id INNER JOIN tb_m_services tms ON tms.id = dtl.service_id ORDER BY hdr.laundry_code", nativeQuery = true)
	List<TransactionDetails> getListDetails() throws Exception;

	@Modifying
	@Query(value = "update tb_r_dtl_laundry set status = 'Picked up' where dtl_code = ?  ", nativeQuery = true)
	void updatePickUpTime(String code) throws Exception;

	@Query(value = "SELECT count(*) as total_data FROM tb_r_dtl_laundry ", nativeQuery = true)
	Long getCountData() throws Exception;

	@Modifying
	@Query(value = "UPDATE tb_r_dtl_laundry SET dtl_description = ?1 unit = ?2, perfume_id = ?3 , service_id = ?4 WHERE id = ?5 ", nativeQuery = true)
	void updateData(String desc, Double unit, Long perfumeId, Long serviceId, Long id) throws Exception;

	@Query(value = "SELECT dtl_code, dtl_description, pickup_time, price, status, unit, hdr_id, perfume_id, service_id FROM tb_r_dtl_laundry WHERE id = ?1", nativeQuery = true)
	TransactionDetails getById(Long id) throws Exception;

}
