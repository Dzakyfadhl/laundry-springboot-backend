package com.lawencon.laundry.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.PerfumesDao;
import com.lawencon.laundry.model.Perfumes;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.PerfumeService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
@Transactional
public class PerfumeServiceImpl extends BaseService implements PerfumeService {

	@Autowired
	@Qualifier(value = "perfume_jpa")
	private PerfumesDao perfumesDao;

	@Override
	public void addPerfume(Perfumes perfume) throws Exception {
		validateInput(perfume);
		perfumesDao.addPerfume(perfume);
	}

	private void validateInput(Perfumes perfume) throws Exception {
		StringBuilder vldMsg = new StringBuilder("Invalid input ");
		int msgLength = vldMsg.length();

		if (perfume.getPerfumeCode() == null || perfume.getPerfumeCode().trim().equals("")) {
			vldMsg.append(", perfume code ");
		}

		if (perfume.getPerfumeName() == null || perfume.getPerfumeName().trim().equals("")) {
			vldMsg.append(", perfume name ");
		}

		if (perfume.getId() != null) {
			vldMsg.append(", id ");
		}

		if (vldMsg.length() > msgLength) {
			throw new Exception(vldMsg.toString());
		}

	}

	@Override
	public Long getCountDataPerfumes() throws Exception {
		return perfumesDao.getCountDataPerfumes();

	}

	@Override
	public List<Perfumes> getListPerfumes() throws Exception {
		return perfumesDao.getListPerfumes();

	}

	@Override
	public Perfumes getByCode(Perfumes perfume) throws Exception {
		return perfumesDao.getByCode(perfume);
	}

	@Override
	public void deleteData(Perfumes perfume) throws Exception {
		Perfumes perfumes = perfumesDao.getByCode(perfume);
		Long id = perfumes.getId();

		Perfumes checkConstraint = perfumesDao.checkConstraint(id);
		if (checkConstraint == null) {
			perfumesDao.deleteData(perfume.getPerfumeCode());
		} else {
			throw new Exception("Can't delete data, constraint with another table!");
		}

	}

	@Override
	public void updateData(Perfumes perfume) throws Exception {
		if (perfume.getId() == null) {
			throw new Exception("Invalid input , id must not null! ");
		}

		Perfumes perfumeDB = perfumesDao.getById(perfume);
		if (perfumeDB == null) {
			throw new Exception("Invalid input , id not found! ");
		}

		if (perfume.getPerfumeCode() == null) {
			perfume.setPerfumeCode(perfumeDB.getPerfumeCode());
		}

		if (perfume.getPerfumeName() == null) {
			perfume.setPerfumeName(perfumeDB.getPerfumeName());
		}

		perfumesDao.updateData(perfume);
	}
}
