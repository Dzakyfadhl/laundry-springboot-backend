package com.lawencon.laundry.dao.jpanative;

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

@Repository(value = "service_jpa")
public class ServicesLaundryDaoJpaNativeImpl extends BaseDao implements ServicesLaundryDao {

	@Autowired
	private ServiceLaundryRepo serviceLaundryRepo;

	@Override
	public void addServices(ServiceLaundry serviceLaundry) throws Exception {
		serviceLaundryRepo.save(serviceLaundry);
	}

	@Override
	public ServiceLaundry getByCode(ServiceLaundry serviceLaundry) throws Exception {
		return serviceLaundryRepo.getByCode(serviceLaundry.getServiceCode());
	}

	@Override
	public List<ServiceLaundry> getListServicesLaundry() throws Exception {
		List<ServiceLaundry> listResult = serviceLaundryRepo.getListServicesLaundry();
		return listResult;
	}

	@Override
	public Integer getServiceDayDuration(ServiceLaundry serviceLaundry) throws Exception {
		return serviceLaundryRepo.getServiceDayDuration(serviceLaundry.getServiceCode());
	}

	@Override
	public Long getCountDataService() throws Exception {
		return serviceLaundryRepo.getCountDataService();
	}

	@Override
	public ServiceLaundry checkConstraint(Long id) throws Exception {
		ServiceLaundry serviceLaundry = serviceLaundryRepo.checkConstraint(id);
		return serviceLaundry == null ? null : serviceLaundry;
	}

	@Override
	public void deleteData(String code) throws Exception {
		serviceLaundryRepo.deleteData(code);

	}
}
