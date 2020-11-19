package com.day.today.health.response.models;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchRatingRespose {
	private String productName;
	private Integer averageRating;
	private Integer totalRating;
	private HashMap<Integer, Integer> individualRating;
}
