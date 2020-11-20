package com.day.today.health.service.helper;

import org.springframework.stereotype.Service;

import com.day.today.health.request.models.RatingRequest;
import com.day.today.health.response.models.ServiceResponse;
import com.day.today.health.utils.Constants;
import com.day.today.health.utils.Constants.Product;

import lombok.extern.log4j.Log4j2;

/**
 * @author Aditi
 *
 */
@Service
@Log4j2
public class RatingServiceHelper {

	/**
	 * This method vaidates add and update rating request
	 * 
	 * @param data
	 * @return
	 */
	public ServiceResponse<String> validateRatingRequest(RatingRequest data) {
		if (null == data) {
			log.info("Request cannot be empty");
			return new ServiceResponse<>("Request cannot be empty", Constants.FAILURE);
		}
		if (null == data.getPatientId()) {
			log.info("Patient Id cannot be empty");
			return new ServiceResponse<>("Patient Id cannot be empty", Constants.FAILURE);
		}
		if (null == data.getProductName() || data.getProductName().isEmpty()) {
			log.info("Product Name cannot be empty");
			return new ServiceResponse<>("Product Name cannot be empty", Constants.FAILURE);
		}
		if (!Product.validateProduct(data.getProductName())) {
			log.info("Product Name Not Valid");
			return new ServiceResponse<>("Product Name Not Valid", Constants.FAILURE);
		}
		if (null == data.getRating()) {
			log.info("Rating cannot be empty");
			return new ServiceResponse<>("Rating cannot be empty", Constants.FAILURE);
		}
		if (data.getRating() < 1 || data.getRating() > 5) {
			log.info("Rating Should be in range of 1-5");
			return new ServiceResponse<>("Rating Should be in range of 1-5", Constants.FAILURE);
		}
		log.info("Request validated");
		return new ServiceResponse<>("Request validated", Constants.SUCCESS);
	}
}
