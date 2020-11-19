package com.day.today.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.day.today.health.request.models.RatingRequest;
import com.day.today.health.request.models.ServiceRequest;
import com.day.today.health.response.models.FetchRatingRespose;
import com.day.today.health.response.models.ServiceResponse;
import com.day.today.health.services.RatingService;

/**
 * @author Aditi
 *
 */
@RestController
@RequestMapping(value = "v1/rating")
public class RatingController {

	@Autowired private RatingService ratingService;

	/**
	 * This API will add rating for a particular product
	 * 
	 * @param request - patientid, productName, rating
	 * @return
	 */
	@PostMapping("add")
	public ServiceResponse<String> addRating(@RequestBody ServiceRequest<RatingRequest> request) {
		return ratingService.addRating(request.getData());
	}

	/**
	 * This API will update rating for a particular product
	 * 
	 * @param request - patientid, productName, rating
	 * @return
	 */
	@PutMapping("update")
	public ServiceResponse<String> updateRating(@RequestBody ServiceRequest<RatingRequest> request) {
		return ratingService.updateRating(request.getData());
	}

	/**
	 * This API will fetch Rating for a particular product
	 * 
	 * @param productName
	 * @return
	 */
	@GetMapping("fetch/{productName}")
	public ServiceResponse<FetchRatingRespose> fetchRating(@PathVariable String productName) {
		return ratingService.fetchRating(productName);
	}
}