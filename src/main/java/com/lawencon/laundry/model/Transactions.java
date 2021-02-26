package com.lawencon.laundry.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_r_hdr_laundry")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "laundry_code", unique = true, length = 100, nullable = false)
	private String laundryCode;

	@Column(name = "laundry_time", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime laundryTime;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Profiles employeeId;

	@ManyToOne
	@JoinColumn(name = "payments_id", nullable = false)
	private PaymentsType paymentId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customers customerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Profiles getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Profiles employeeId) {
		this.employeeId = employeeId;
	}

	public PaymentsType getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(PaymentsType paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDateTime getLaundryTime() {
		return laundryTime;
	}

	public void setLaundryTime(LocalDateTime laundryTime) {
		this.laundryTime = laundryTime;
	}

	public String getLaundryCode() {
		return laundryCode;
	}

	public void setLaundryCode(String laundryCode) {
		this.laundryCode = laundryCode;
	}

	public Customers getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}

}
