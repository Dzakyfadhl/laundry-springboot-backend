package com.lawencon.laundry.dao;

import java.util.List;

import com.lawencon.laundry.model.ServiceLaundry;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface ServicesLaundryDao {

	void addServices(ServiceLaundry serviceLaundry) throws Exception;

	List<ServiceLaundry> getListServicesLaundry() throws Exception;

	Integer getServiceDayDuration(ServiceLaundry serviceLaundry) throws Exception;

	ServiceLaundry getByCode(ServiceLaundry serviceLaundry) throws Exception;

	Long getCountDataService() throws Exception;

	void deleteData(String code) throws Exception;

	ServiceLaundry checkConstraint(Long id) throws Exception;
}
