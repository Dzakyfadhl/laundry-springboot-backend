package com.lawencon.laundry.dao.hnative;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.PerfumesDao;
import com.lawencon.laundry.model.Perfumes;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "perfume_hib")
public class PerfumesDaoHibernateImpl extends BaseDao implements PerfumesDao {

	@Override
	public void addPerfume(Perfumes perfume) throws Exception {
		String sql = bBuilder("INSERT INTO tb_m_perfumes (perfume_code, perfume_name) values (?,?) ").toString();

		em.createNativeQuery(sql).setParameter(1, perfume.getPerfumeCode()).setParameter(2, perfume.getPerfumeName())
				.executeUpdate();

	}

	@Override
	public Long getCountDataPerfumes() throws Exception {
		String sql = bBuilder("SELECT count(*) as total_data FROM tb_m_perfumes").toString();
		return Long.valueOf(em.createNativeQuery(sql).getSingleResult().toString());
	}

	@Override
	public List<Perfumes> getListPerfumes() throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_perfumes").toString();
		List<Perfumes> listResult = em.createNativeQuery(sql, Perfumes.class).getResultList();
		return listResult;
	}

	@Override
	public Perfumes getByCode(Perfumes perfume) throws Exception {
		String sql = "SELECT * FROM tb_m_perfumes where perfume_code = ?1  ";
		Object result = em.createNativeQuery(sql).setParameter(1, perfume.getPerfumeCode()).getResultList().get(0);
		Perfumes resultc = (Perfumes) result;
		return resultc;
	}

	@Override
	public Perfumes checkConstraint(Long id) throws Exception {
		String sql = "SELECT * FROM tb_m_perfumes WHERE id_perfume= ?1 AND id_perfume in (SELECT perfume_id FROM tb_r_dtl_laundry) LIMIT 1 ";
		Object result = em.createNativeQuery(sql).setParameter(1, id).getResultList().get(0);
		Perfumes resultc = (Perfumes) result;
		return resultc;
	}

	@Override
	public void deleteData(String code) throws Exception {
		String sql = "DELETE FROM tb_m_perfumes WHERE perfume_code = ?1 ";
		em.createNativeQuery(sql).setParameter(1, code).executeUpdate();
	}

	@Override
	public Perfumes getById(Perfumes perfume) throws Exception {
		String sql = "SELECT * FROM tb_m_perfumes WHERE id_perfume = ?1";
		Object result = em.createNativeQuery(sql).setParameter(1, perfume.getId()).getResultList().get(0);
		Perfumes resultc = (Perfumes) result;
		return resultc;
	}

	@Override
	public void updateData(Perfumes perfume) throws Exception {
		String sql = "UPDATE tb_m_perfumes SET perfume_code = ?1 , perfume_name = ?2  WHERE id = ?3";
		em.createNativeQuery(sql).setParameter(1, perfume.getPerfumeCode()).setParameter(2, perfume.getPerfumeName())
				.setParameter(3, perfume.getId());

	}
}
