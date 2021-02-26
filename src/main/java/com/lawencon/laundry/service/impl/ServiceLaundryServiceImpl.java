package com.lawencon.laundry.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.ServicesLaundryDao;
import com.lawencon.laundry.model.ServiceLaundry;
import com.lawencon.laundry.service.BaseService;
import com.lawencon.laundry.service.ServiceLaundryService;

/**
 * @author Dzaky Fadhilla Guci
 */

@Service
@Transactional
public class ServiceLaundryServiceImpl extends BaseService implements ServiceLaundryService {

	@Autowired
	@Qualifier(value = "service_jpa")
	private ServicesLaundryDao servicesLaundryDao;

	@Override
	public void addServices(ServiceLaundry serviceLaundry) throws Exception {
		validateInput(serviceLaundry);
		servicesLaundryDao.addServices(serviceLaundry);

	}

	private void validateInput(ServiceLaundry sl) throws Exception {
		StringBuilder vldMsg = new StringBuilder("Invalid input ");
		int msgLength = vldMsg.length();

		if (sl.getServiceCode() == null || sl.getServiceCode().trim().equals("")) {
			vldMsg.append(", service code ");
		}

		if (sl.getServiceName() == null || sl.getServiceName().trim().equals("")) {
			vldMsg.append(", service name ");
		}

		if (sl.getServicePrice() == null || sl.getServicePrice().signum() < 1) {
			vldMsg.append(", service price ");
		}

		if (sl.getDayDuration() == null || sl.getDayDuration() < 1) {
			vldMsg.append(", day duration ");
		}

		if (sl.getId() != null) {
			vldMsg.append(", id ");
		}

		if (vldMsg.length() > msgLength) {
			throw new Exception(vldMsg.toString());
		}

	}

	@Override
	public ServiceLaundry getByCode(ServiceLaundry serviceLaundry) throws Exception {
		return servicesLaundryDao.getByCode(serviceLaundry);
	}

	@Override
	public List<ServiceLaundry> getListServicesLaundry() throws Exception {
		return servicesLaundryDao.getListServicesLaundry();

	}

	@Override
	public Integer getServiceDayDuration(ServiceLaundry serviceLaundry) throws Exception {
		return servicesLaundryDao.getServiceDayDuration(serviceLaundry);

	}

	@Override
	public Long getCountDataService() throws Exception {
		return servicesLaundryDao.getCountDataService();

	}

	@Override
	public void deleteData(ServiceLaundry serviceLaundry) throws Exception {
		ServiceLaundry s = servicesLaundryDao.getByCode(serviceLaundry);
		Long id = s.getId();

		ServiceLaundry checkConstraint = servicesLaundryDao.checkConstraint(id);
		if (checkConstraint == null) {
			servicesLaundryDao.deleteData(serviceLaundry.getServiceCode());
		} else {
			throw new Exception("Can't delete data, constraint with another table!");
		}
	}

}
