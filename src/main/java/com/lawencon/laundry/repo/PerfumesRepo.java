package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Perfumes;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface PerfumesRepo extends JpaRepository<Perfumes, Long> {

	@Query(value = "SELECT count(*) as total_data FROM tb_m_perfumes", nativeQuery = true)
	Long getCountDataPerfumes() throws Exception;

	@Query(value = "SELECT * FROM tb_m_perfumes", nativeQuery = true)
	List<Perfumes> getListPerfumes() throws Exception;

	@Query(value = "SELECT * FROM tb_m_perfumes where perfume_code = ?1 ", nativeQuery = true)
	Perfumes getByCode(String code) throws Exception;

	@Query(value = "SELECT * FROM tb_m_perfumes WHERE id_perfume= ?1 AND id_perfume in (SELECT perfume_id FROM tb_r_dtl_laundry) LIMIT 1 ", nativeQuery = true)
	Perfumes checkConstraint(Long id) throws Exception;

	@Modifying
	@Query(value = "DELETE FROM tb_m_perfumes WHERE perfume_code = ?1", nativeQuery = true)
	void deleteData(String code) throws Exception;

	@Query(value = "SELECT * FROM tb_m_perfumes WHERE id_perfume = ?1 ", nativeQuery = true)
	Perfumes getById(Long id) throws Exception;

	@Query(value = "UPDATE tb_m_perfumes SET perfume_code = ?1 , perfume_name = ?2  WHERE id = ?3", nativeQuery = true)
	void updateData(String code, String name, Long id) throws Exception;

}
