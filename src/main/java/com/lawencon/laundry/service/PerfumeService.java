package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Perfumes;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface PerfumeService {

	void addPerfume(Perfumes perfume) throws Exception;

	List<Perfumes> getListPerfumes() throws Exception;

	Long getCountDataPerfumes() throws Exception;

	Perfumes getByCode(Perfumes perfume) throws Exception;

	void deleteData(Perfumes perfume) throws Exception;

	void updateData(Perfumes perfume) throws Exception;
}
