package com.lawencon.laundry.dao.hnative;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.ServicesLaundryDao;
import com.lawencon.laundry.model.ServiceLaundry;
import com.lawencon.laundry.repo.ServiceLaundryRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "service_hib")
public class ServicesLaundryDaoHibernateNativeImpl extends BaseDao implements ServicesLaundryDao {

	@Autowired
	private ServiceLaundryRepo serviceLaundryRepo;

	@Override
	public void addServices(ServiceLaundry serviceLaundry) throws Exception {
		String sql = bBuilder(
				"INSERT INTO tb_m_services (service_code, service_name, service_price, day_duration) values (?,?,?,?) ")
						.toString();
		em.createNativeQuery(sql).setParameter(1, serviceLaundry.getServiceCode())
				.setParameter(2, serviceLaundry.getServiceName()).setParameter(3, serviceLaundry.getServicePrice())
				.setParameter(4, serviceLaundry.getDayDuration()).executeUpdate();
	}

	@Override
	public ServiceLaundry getByCode(ServiceLaundry serviceLaundry) throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_services WHERE service_code = ? ").toString();

		List<ServiceLaundry> listResult = em.createNativeQuery(sql, ServiceLaundry.class)
				.setParameter(1, serviceLaundry.getServiceCode()).getResultList();
		return getResultModel(listResult);
	}

	@Override
	public List<ServiceLaundry> getListServicesLaundry() throws Exception {
		String sql = bBuilder("SELECT * FROM tb_m_services").toString();
		List<ServiceLaundry> listResult = em.createNativeQuery(sql, ServiceLaundry.class).getResultList();
		return listResult;
	}

	@Override
	public Integer getServiceDayDuration(ServiceLaundry serviceLaundry) throws Exception {
		String sql = bBuilder("SELECT day_duration FROM tb_m_services WHERE service_code =? ").toString();

		return (Integer) em.createNativeQuery(sql).setParameter(1, serviceLaundry.getServiceCode()).getSingleResult();
	}

	@Override
	public Long getCountDataService() throws Exception {
		String sql = bBuilder("SELECT count(*) as total_data FROM tb_m_services").toString();
		return Long.valueOf(em.createNativeQuery(sql).getSingleResult().toString());
	}

	@Override
	public ServiceLaundry checkConstraint(Long id) throws Exception {
		String sql = "SELECT * FROM tb_m_services WHERE id = ?1 AND id in (SELECT service_id FROM tb_r_dtl_laundry) LIMIT 1";
		Object result = em.createNativeQuery(sql).setParameter(1, id).getResultList().get(0);

		ServiceLaundry results = (ServiceLaundry) result;

		return results != null ? results : null;
	}

	@Override
	public void deleteData(String code) throws Exception {
		String sql = "DELETE FROM tb_m_services WHERE service_code = ?1";

		em.createNativeQuery(sql).setParameter(1, code).executeUpdate();

	}
}
