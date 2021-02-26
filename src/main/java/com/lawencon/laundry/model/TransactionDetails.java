package com.lawencon.laundry.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Dzaky Fadhilla Guci
 */

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "tb_r_dtl_laundry")
//@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer", "hdrId" })
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dtl_code", unique = true, length = 100, nullable = false)
	private String dtlCode;

	@Column(name = "dtl_description", length = 100, nullable = false)
	private String dtlDesc;

	@Column(nullable = false)
	private Double unit;

	@Column(name = "pickup_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime pickupTime;

	@Column(length = 30, nullable = false)
	private String status;

	@Column(nullable = false)
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hdr_id", nullable = false)
	private Transactions hdrId;

	@ManyToOne
	@JoinColumn(name = "perfume_id", nullable = false)
	private Perfumes perfumeId;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private ServiceLaundry serviceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDtlDesc() {
		return dtlDesc;
	}

	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}

	public Double getUnit() {
		return unit;
	}

	public void setUnit(Double unit) {
		this.unit = unit;
	}

	public LocalDateTime getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(LocalDateTime pickupTime) {
		this.pickupTime = pickupTime;
	}

	public ServiceLaundry getServiceId() {
		return serviceId;
	}

	public void setServiceId(ServiceLaundry serviceId) {
		this.serviceId = serviceId;
	}

	public Transactions getHdrId() {
		return hdrId;
	}

	public void setHdrId(Transactions hdrId) {
		this.hdrId = hdrId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Perfumes getPerfumeId() {
		return perfumeId;
	}

	public void setPerfumeId(Perfumes perfumeId) {
		this.perfumeId = perfumeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDtlCode() {
		return dtlCode;
	}

	public void setDtlCode(String dtlCode) {
		this.dtlCode = dtlCode;
	}

}
