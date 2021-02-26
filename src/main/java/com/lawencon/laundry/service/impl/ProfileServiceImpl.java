package com.lawencon.laundry.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.ProfilesDao;
import com.lawencon.laundry.model.Profiles;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.ProfileService;
import com.lawencon.laundry.service.UserService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
public class ProfileServiceImpl extends BaseService implements ProfileService {

	@Autowired
	@Qualifier(value = "profile_jpa")
	private ProfilesDao profilesDao;

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public void addProfile(Profiles profile) throws Exception {
		validateInput(profile);
		Users us = userService.findByUsername(profile.getUserId().getUsername());
		if (us == null) {
			throw new Exception("Error, can't find user!");
		}
		profile.setUserId(us);
		profilesDao.addProfile(profile);

	}

	private void validateInput(Profiles profile) throws Exception {
		StringBuilder vldMsg = new StringBuilder("Invalid input ");
		int msgLength = vldMsg.length();

		if (profile.getEmployeeCode() == null || profile.getEmployeeCode().trim().equals("")) {
			vldMsg.append(", employee code ");
		}

		if (profile.getFullName() == null || profile.getFullName().trim().equals("")) {
			vldMsg.append(", full name ");
		}

		if (profile.getPhone() == null || profile.getPhone().trim().equals("") || profile.getPhone().length() > 15) {
			vldMsg.append(", phone ");
		}

		if (profile.getAddress() == null || profile.getAddress().trim().equals("")) {
			vldMsg.append(", address ");
		}

		if (profile.getUserId() == null || profile.getUserId().getUsername().trim().equals("")) {
			vldMsg.append(", userId ");
		}

		if (profile.getId() != null) {
			vldMsg.append(", id ");
		}

		if (vldMsg.length() > msgLength) {
			throw new Exception(vldMsg.toString());
		}

	}

	@Override
	@Transactional
	public Profiles getByCode(Profiles profile) throws Exception {
		return profilesDao.getByCode(profile);
	}

	@Override
	@Transactional
	public List<Profiles> getListProfiles() throws Exception {
		return profilesDao.getListProfiles();

	}

	@Override
	public Profiles getByUserId(Profiles profile) throws Exception {
		return profilesDao.getByUserId(profile);
	}

	@Override
	public void deleteData(Profiles profile) throws Exception {
		Profiles prof = profilesDao.getByCode(profile);
		Long id = prof.getId();

		Profiles checkConstraint = profilesDao.checkConstraint(id);
		if (checkConstraint == null) {
			profilesDao.deleteData(profile.getEmployeeCode());
		} else {
			throw new Exception("Can't delete data, constraint with another table!");
		}
	}

	@Override
	public void updateData(Profiles profile) throws Exception {
		if (profile.getId() == null) {
			throw new Exception("Invalid input , id must not null! ");
		}

		Profiles profileDB = profilesDao.getById(profile);
		if (profileDB == null) {
			throw new Exception("Invalid input , id not found! ");
		}

		if (profile.getEmployeeCode() == null) {
			profile.setEmployeeCode(profileDB.getEmployeeCode());
		}

		if (profile.getFullName() == null) {
			profile.setFullName(profileDB.getFullName());
		}

		if (profile.getPhone() == null) {
			profile.setPhone(profileDB.getPhone());
		}

		if (profile.getAddress() == null) {
			profile.setAddress(profileDB.getAddress());
		}

		profilesDao.updateData(profile);

	}

}
