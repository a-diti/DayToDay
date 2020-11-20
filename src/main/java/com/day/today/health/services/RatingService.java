package com.day.today.health.services;

import com.day.today.health.request.models.RatingRequest;
import com.day.today.health.response.models.FetchRatingRespose;
import com.day.today.health.response.models.ServiceResponse;

/**
 * @author Aditi
 *
 */
public interface RatingService {

	/**
	 * This API will add rating for a particular product
	 * 
	 * @param data
	 * @return
	 */
	ServiceResponse<String> addRating(RatingRequest data);

	/**
	 * This API will update rating for a particular product
	 * 
	 * @param data
	 * @return
	 */
	ServiceResponse<String> updateRating(RatingRequest data);

	/**
	 * This API will fetch Rating for a particular product
	 * 
	 * @param productName
	 * @return
	 */
	ServiceResponse<FetchRatingRespose> fetchRating(String productName);

}
