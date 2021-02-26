package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Perfumes;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface PerfumesDao {

	void addPerfume(Perfumes perfume) throws Exception;

	List<Perfumes> getListPerfumes() throws Exception;

	Long getCountDataPerfumes() throws Exception;

	Perfumes getByCode(Perfumes perfume) throws Exception;

	void deleteData(String code) throws Exception;

	Perfumes checkConstraint(Long id) throws Exception;

	void updateData(Perfumes perfume) throws Exception;

	Perfumes getById(Perfumes perfume) throws Exception;
}
