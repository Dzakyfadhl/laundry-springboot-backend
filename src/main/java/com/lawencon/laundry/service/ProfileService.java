package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.Profiles;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface ProfileService {

	List<Profiles> getListProfiles() throws Exception;

	void addProfile(Profiles profile) throws Exception;

	Profiles getByCode(Profiles profile) throws Exception;

	Profiles getByUserId(Profiles profile) throws Exception;

	void deleteData(Profiles profile) throws Exception;

	void updateData(Profiles profile) throws Exception;
}
