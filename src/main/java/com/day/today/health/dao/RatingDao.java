package com.day.today.health.dao;

import java.util.List;

import com.day.today.health.data.models.Ratings;

/**
 * @author Aditi
 *
 */
public interface RatingDao {

	/**
	 * This will save new entry in the database
	 * 
	 * @param ratings
	 */
	void saveRatings(Ratings ratings);

	/**
	 * This will update existing entry in the database
	 * 
	 * @param ratings
	 */
	void updateRatings(Ratings ratings);

	/**
	 * This will fetch rating using patientId and product name
	 * 
	 * @param patientId
	 * @param productName
	 * @return
	 */
	Ratings getRatingByIdAndProdName(Integer patientId, String productName);

	/**
	 * This will fetch all rating of provided product
	 * 
	 * @param productName
	 * @return
	 */
	List<Ratings> fetchAllByProductName(String productName);

}
