package com.lawencon.laundry.helper;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Dzaky Fadhilla Guci
 */
@JsonInclude(Include.NON_NULL)
public class CustomResponse<D> {

	private Integer code;
	private HttpStatus status;
	private D data;
	private String message;

	public CustomResponse(D data, HttpStatus status) {
		this.code = status.value();
		this.status = status;
		this.data = data;
	}

	public CustomResponse(D data, HttpStatus status, String message) {
		this.code = status.value();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public CustomResponse(HttpStatus status, String message) {
		this.code = status.value();
		this.status = status;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
