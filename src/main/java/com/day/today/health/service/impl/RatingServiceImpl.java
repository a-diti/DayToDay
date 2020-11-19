package com.day.today.health.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.day.today.health.dao.RatingDao;
import com.day.today.health.data.models.Ratings;
import com.day.today.health.request.models.RatingRequest;
import com.day.today.health.response.models.FetchRatingRespose;
import com.day.today.health.response.models.ServiceResponse;
import com.day.today.health.service.helper.RatingServiceHelper;
import com.day.today.health.services.RatingService;
import com.day.today.health.utils.Constants;
import com.day.today.health.utils.Constants.Product;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RatingServiceImpl implements RatingService {
	@Autowired private RatingServiceHelper ratingServiceHelper;
	@Autowired private RatingDao ratingDao;

	@Override
	public ServiceResponse<String> addRating(RatingRequest data) {
		ServiceResponse<String> response = new ServiceResponse<>();
		try {
			response = ratingServiceHelper.validateRatingRequest(data);
			if (null != response && null != response.getStatus()
					&& response.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
				Ratings rating = ratingDao.getRatingByIdAndProdName(data.getPatientId(), data.getProductName());
				if (null == rating) {
					ratingDao.saveRatings(new Ratings(data.getPatientId(), data.getProductName(), data.getRating()));
					log.info("Rating saved");
					response = new ServiceResponse<>("Rating saved", Constants.SUCCESS);
				} else {
					response = new ServiceResponse<>("Rating Already Given", Constants.FAILURE);
				}
			}
		} catch (Exception e) {
			log.info(Constants.ERROR);
			response = new ServiceResponse<>(Constants.ERROR_MESSAGE, Constants.FAILURE);
		}
		return response;
	}

	@Override
	public ServiceResponse<String> updateRating(RatingRequest data) {
		ServiceResponse<String> response = new ServiceResponse<>();
		try {
			response = ratingServiceHelper.validateRatingRequest(data);
			if (null != response && null != response.getStatus()
					&& response.getStatus().equalsIgnoreCase(Constants.SUCCESS)) {
				Ratings rating = ratingDao.getRatingByIdAndProdName(data.getPatientId(), data.getProductName());
				if (null != rating) {
					rating.setRating(data.getRating());
					ratingDao.updateRatings(rating);
					log.info("Rating Updated");
					response = new ServiceResponse<>("Rating Updated", Constants.SUCCESS);
				} else {
					response = new ServiceResponse<>("Data Not Found", Constants.FAILURE);
				}
			}
		} catch (Exception e) {
			log.info(Constants.ERROR);
			response = new ServiceResponse<>(Constants.ERROR_MESSAGE, Constants.FAILURE);
		}
		return response;
	}

	@Override
	public ServiceResponse<FetchRatingRespose> fetchRating(String productName) {
		ServiceResponse<FetchRatingRespose> response = new ServiceResponse<>();
		try {
			if (null != productName && !productName.isEmpty() && Product.validateProduct(productName)) {
				List<Ratings> ratingList = ratingDao.fetchAllByProductName(productName);
				if (null != ratingList && !ratingList.isEmpty()) {
					Integer ratingSum = 0;
					HashMap<Integer, Integer> ratingCount = new HashMap<>();
					for (Ratings rating : ratingList) {
						ratingSum += rating.getRating();
						ratingCount.put(rating.getRating(), ratingCount.getOrDefault(rating.getRating(), 0) + 1);
					}
					FetchRatingRespose fetchResponse = new FetchRatingRespose(productName,
							ratingSum / ratingList.size(), ratingList.size(), ratingCount);
					response = new ServiceResponse<>("Rating Fetched", Constants.SUCCESS, fetchResponse);
				} else {
					response = new ServiceResponse<>("Data Not Found", Constants.FAILURE);
				}
			} else {
				response = new ServiceResponse<>("Product Name Not Valid", Constants.FAILURE);
			}
		} catch (Exception e) {
			log.info(Constants.ERROR);
			response = new ServiceResponse<>(Constants.ERROR_MESSAGE, Constants.FAILURE);
		}
		return response;
	}
}
