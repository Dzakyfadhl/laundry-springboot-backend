package com.lawencon.laundry.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Dzaky Fadhilla Guci
 */

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "tb_m_services")
public class ServiceLaundry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "service_code", unique = true, length = 100, nullable = false)
	private String serviceCode;

	@Column(name = "service_name", length = 50, nullable = false)
	private String serviceName;

	@Column(name = "service_price", nullable = false)
	private BigDecimal servicePrice;

	@Column(name = "day_duration", nullable = false)
	private Integer dayDuration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getDayDuration() {
		return dayDuration;
	}

	public void setDayDuration(Integer dayDuration) {
		this.dayDuration = dayDuration;
	}

	public BigDecimal getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

}
