package com.lawencon.laundry.dao.hnative;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.UsersDao;
import com.lawencon.laundry.model.Roles;
import com.lawencon.laundry.model.Users;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "user_hib")
public class UsersDaoHibernateImpl extends BaseDao implements UsersDao {

	@Override
	public void addUser(Users user) throws Exception {
		String sql = bBuilder(
				"INSERT INTO tb_m_users (username, passwords, roles_id) VALUES (?,?, (SELECT id FROM tb_m_roles WHERE role_code = ?))")
						.toString();

		em.createNativeQuery(sql).setParameter(1, user.getUsername()).setParameter(2, user.getPassword())
				.setParameter(3, user.getRolesId().getRoleCode()).executeUpdate();

	}

	@Override
	public Long getCountData() throws Exception {
		String sql = bBuilder("SELECT count(*) as total_data FROM tb_m_users").toString();
		return Long.valueOf(em.createNativeQuery(sql).getSingleResult().toString());
	}

	@Override
	public List<Users> getAllUsers() throws Exception {
		String sql = bBuilder(
				"SELECT u.id, u.username, r.id as id_roles, r.role_code  FROM tb_m_users u INNER JOIN tb_m_roles r")
						.toString();

		List<Users> listUsers = new ArrayList<Users>();
		List<?> listObj = em.createNativeQuery(sql).getResultList();

		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users u = new Users();
			u.setId(Long.valueOf(objArr[0].toString()));
			u.setUsername((String) objArr[1]);

			Roles roles = new Roles();
			roles.setId(Long.valueOf(objArr[2].toString()));
			roles.setRoleCode((String) objArr[3]);

			u.setRolesId(roles);
			listUsers.add(u);

		});

		return listUsers;
	}

	@Override
	public Users findByUsername(String username) throws Exception {
		String sql = "SELECT * FROM tb_m_users WHERE username = ?1";
		Object result = em.createNativeQuery(sql).getResultList().get(0);
		Users user = (Users) result;
		return user == null ? null : user;
	}

	@Override
	public void updateData(Users user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Users getById(Users user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
