package com.day.today.health.response.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {
	private String message;
	private String status;
	private T data;

	public ServiceResponse(String message, String status) {
		this.message = message;
		this.status = status;
	}
}

