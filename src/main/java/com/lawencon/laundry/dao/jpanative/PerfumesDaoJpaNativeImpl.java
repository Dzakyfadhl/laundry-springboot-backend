package com.lawencon.laundry.dao.jpanative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.PerfumesDao;
import com.lawencon.laundry.model.Perfumes;
import com.lawencon.laundry.repo.PerfumesRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "perfume_jpa")
public class PerfumesDaoJpaNativeImpl extends BaseDao implements PerfumesDao {

	@Autowired
	private PerfumesRepo perfumesRepo;

	@Override
	public void addPerfume(Perfumes perfume) throws Exception {
		perfumesRepo.save(perfume);
	}

	@Override
	public Long getCountDataPerfumes() throws Exception {
		return perfumesRepo.getCountDataPerfumes();
	}

	@Override
	public List<Perfumes> getListPerfumes() throws Exception {
		return perfumesRepo.getListPerfumes();
	}

	@Override
	public Perfumes getByCode(Perfumes perfume) throws Exception {
		return perfumesRepo.getByCode(perfume.getPerfumeCode());
	}

	@Override
	public Perfumes checkConstraint(Long id) throws Exception {
		Perfumes perfumes = perfumesRepo.checkConstraint(id);
		return perfumes == null ? null : perfumes;
	}

	@Override
	public void deleteData(String code) throws Exception {
		perfumesRepo.deleteData(code);
	}

	@Override
	public Perfumes getById(Perfumes perfume) throws Exception {
		return perfumesRepo.getById(perfume.getId());
	}

	@Override
	public void updateData(Perfumes perfume) throws Exception {
		perfumesRepo.updateData(perfume.getPerfumeCode(), perfume.getPerfumeName(), perfume.getId());
	}
}
