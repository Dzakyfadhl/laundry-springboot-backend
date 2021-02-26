package com.lawencon.laundry.dao.hnative;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.ProfilesDao;
import com.lawencon.laundry.model.Profiles;
import com.lawencon.laundry.model.Users;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "profile_hib")
public class ProfilesDaoHibernateImpl extends BaseDao implements ProfilesDao {

	@Override
	public void addProfile(Profiles profile) throws Exception {
		String sql = bBuilder("INSERT INTO tb_m_profiles (employee_code, fullname, phone, address, users_id)",
				"values (?,?,?,?, (SELECT id FROM tb_m_users WHERE username = ?)) ").toString();

		em.createNativeQuery(sql).setParameter(1, profile.getEmployeeCode()).setParameter(2, profile.getFullName())
				.setParameter(3, profile.getPhone()).setParameter(4, profile.getAddress())
				.setParameter(5, profile.getUserId().getUsername()).executeUpdate();
	}

	@Override
	public Profiles getByCode(Profiles profile) throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_profiles WHERE employee_code = ? ").toString();

		Object result = em.createNativeQuery(sql).setParameter(1, profile.getEmployeeCode()).getResultList().get(0);

		Profiles resultProf = (Profiles) result;

		return resultProf != null ? resultProf : null;

	}

	@Override
	public List<Profiles> getListProfiles() throws Exception {

		String sql = bBuilder(
				"SELECT p.employee_code, p.fullname, p.phone, p.address, u.username FROM tb_m_profiles p INNER JOIN tb_m_users u on u.id = p.users_id ")
						.toString();

		List<Profiles> listProfiles = new ArrayList<>();
		List<?> listObj = em.createNativeQuery(sql).getResultList();

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
		String sql = bBuilder("SELECT * FROM tb_m_profiles WHERE users_id = ?").toString();

		Object result = em.createNativeQuery(sql).setParameter(1, profile.getUserId().getId()).getResultList().get(0);

		Profiles resultProf = (Profiles) result;

		return resultProf != null ? resultProf : null;

	}

	@Override
	public Profiles checkConstraint(Long id) throws Exception {
		String sql = "SELECT * FROM tb_m_profiles WHERE id = ?1 AND id in (SELECT employee_id FROM tb_r_hdr_shippings) LIMIT 1";
		Object result = em.createNativeQuery(sql).setParameter(1, id).getResultList().get(0);
		Profiles resultc = (Profiles) result;
		return resultc;
	}

	@Override
	public void deleteData(String code) throws Exception {
		String sql = "DELETE FROM tb_m_profiles WHERE employee_code = ?1";
		em.createNativeQuery(sql).setParameter(1, code).executeUpdate();
	}

	@Override
	public void updateData(Profiles profile) throws Exception {
		String sql = "UPDATE tb_m_profiles SET employee_code = ?1 , fullname=?2 , phone=?3 , address=?4 ";
		em.createNativeQuery(sql).setParameter(1, profile.getEmployeeCode()).setParameter(2, profile.getFullName())
				.setParameter(3, profile.getPhone()).setParameter(4, profile.getAddress()).executeUpdate();

	}

	@Override
	public Profiles getById(Profiles profile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
