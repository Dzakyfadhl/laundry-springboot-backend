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
@Table(name = "tb_m_roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "role_name", unique = true, length = 50, nullable = false)
//	private String rolesName;

	@Column(name = "role_code", unique = true, length = 50, nullable = false)
	private String roleCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getRolesName() {
//		return rolesName;
//	}
//
//	public void setRolesName(String rolesName) {
//		this.rolesName = rolesName;
//	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
