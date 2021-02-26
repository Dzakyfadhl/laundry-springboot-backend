package com.lawencon.laundry.model;

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
@Table(name = "tb_m_perfumes")
public class Perfumes {

	@Id
	@Column(name = "id_perfume")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "perfume_code", unique = true, length = 100, nullable = false)
	private String perfumeCode;

	@Column(name = "perfume_name", length = 30, nullable = false)
	private String perfumeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerfumeCode() {
		return perfumeCode;
	}

	public void setPerfumeCode(String perfumeCode) {
		this.perfumeCode = perfumeCode;
	}

	public String getPerfumeName() {
		return perfumeName;
	}

	public void setPerfumeName(String perfumeName) {
		this.perfumeName = perfumeName;
	}

}
