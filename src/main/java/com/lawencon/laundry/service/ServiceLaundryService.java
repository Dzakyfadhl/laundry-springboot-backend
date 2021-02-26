package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.model.ServiceLaundry;

/**
 * @author Dzaky Fadhilla Guci
 */

public interface ServiceLaundryService {

	void addServices(ServiceLaundry serviceLaundry) throws Exception;

	List<ServiceLaundry> getListServicesLaundry() throws Exception;

	Integer getServiceDayDuration(ServiceLaundry serviceLaundry) throws Exception;

	ServiceLaundry getByCode(ServiceLaundry serviceLaundry) throws Exception;

	Long getCountDataService() throws Exception;

	void deleteData(ServiceLaundry serviceLaundry) throws Exception;
}
