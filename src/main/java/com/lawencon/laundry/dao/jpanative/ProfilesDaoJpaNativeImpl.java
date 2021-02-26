package com.lawencon.laundry.dao.jpanative;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.ProfilesDao;
import com.lawencon.laundry.model.Profiles;
import com.lawencon.laundry.model.Users;
import com.lawencon.laundry.repo.ProfilesRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "profile_jpa")
public class ProfilesDaoJpaNativeImpl extends BaseDao implements ProfilesDao {

	@Autowired
	private ProfilesRepo profilesRepo;

	@Override
	public void addProfile(Profiles profile) throws Exception {
		profilesRepo.save(profile);
	}

	@Override
	public Profiles getByCode(Profiles profile) throws Exception {
		return profilesRepo.getByCode(profile.getEmployeeCode());
	}

	@Override
	public List<Profiles> getListProfiles() throws Exception {
		List<Profiles> listProfiles = new ArrayList<>();
		List<?> listObj = profilesRepo.getListProfiles();

		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Profiles profiles = new Profiles();
			profiles.setEmployeeCode((String) objArr[0]);
			profiles.setFullName((String) objArr[1]);
			profiles.setPhone((String) objArr[2]);
			profiles.setAddress((String) objArr[3]);

			Users u = new Users();
			u.setUsername((String) objArr[4]);

			profiles.setUserId(u);

			listProfiles.add(profiles);

		});
		return listProfiles;
	}

	@Override
	public Profiles getByUserId(Profiles profile) throws Exception {
		return profilesRepo.getByUserId(profile.getUserId().getId());
	}

	@Override
	public Profiles checkConstraint(Long id) throws Exception {
		Profiles profiles = profilesRepo.checkConstraint(id);
		return profiles == null ? null : profiles;
	}

	@Override
	public void deleteData(String code) throws Exception {
		profilesRepo.deleteData(code);
	}

	@Override
	public void updateData(Profiles profile) throws Exception {
		profilesRepo.updateData(profile.getEmployeeCode(), profile.getFullName(), profile.getPhone(),
				profile.getAddress(), profile.getId());

	}

	@Override
	public Profiles getById(Profiles profile) throws Exception {
		return profilesRepo.getById(profile.getId());
	}
}
