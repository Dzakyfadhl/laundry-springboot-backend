package com.lawencon.laundry.dao.hnative;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.RolesDao;
import com.lawencon.laundry.model.Roles;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "role_hib")
public class RolesDaoHibernateImpl extends BaseDao implements RolesDao {

	@Override
	public Roles getIdByCode(String roleCode) throws Exception {

		Object result = em.createNativeQuery("SELECT * FROM tb_m_roles WHERE role_code = ?1").setParameter(1, roleCode)
				.getResultList().get(0);

		return (Roles) result;
	}
}
