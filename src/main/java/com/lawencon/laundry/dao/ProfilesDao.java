package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.Profiles;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface ProfilesDao {

	List<Profiles> getListProfiles() throws Exception;

	void addProfile(Profiles profile) throws Exception;

	Profiles getByCode(Profiles profile) throws Exception;

	Profiles getByUserId(Profiles profile) throws Exception;

	void deleteData(String code) throws Exception;

	Profiles checkConstraint(Long id) throws Exception;

	Profiles getById(Profiles profile) throws Exception;

	void updateData(Profiles profile) throws Exception;

}
