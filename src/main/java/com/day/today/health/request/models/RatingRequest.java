package com.day.today.health.request.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
	private Integer patientId;
	private String productName;
	private Integer rating;
}
