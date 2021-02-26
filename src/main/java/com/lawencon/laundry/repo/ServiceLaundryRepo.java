package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.ServiceLaundry;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface ServiceLaundryRepo extends JpaRepository<ServiceLaundry, Long> {

	@Query(value = "SELECT * FROM tb_m_services WHERE service_code = ?", nativeQuery = true)
	ServiceLaundry getByCode(String code) throws Exception;

	@Query(value = "SELECT * FROM tb_m_services", nativeQuery = true)
	List<ServiceLaundry> getListServicesLaundry() throws Exception;

	@Query(value = "SELECT day_duration FROM tb_m_services WHERE service_code =?", nativeQuery = true)
	Integer getServiceDayDuration(String code) throws Exception;

	@Query(value = "SELECT count(*) as total_data FROM tb_m_services", nativeQuery = true)
	Long getCountDataService() throws Exception;

	@Query(value = "SELECT * FROM tb_m_services WHERE id = ?1 AND id in (SELECT service_id FROM tb_r_dtl_laundry) LIMIT 1 ", nativeQuery = true)
	ServiceLaundry checkConstraint(Long id) throws Exception;

	@Modifying
	@Query(value = "DELETE FROM tb_m_services WHERE service_code = ?1", nativeQuery = true)
	void deleteData(String code) throws Exception;
}
