package com.day.today.health.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.day.today.health.dao.RatingDao;
import com.day.today.health.data.models.Ratings;
import com.day.today.health.utils.Constants;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("unchecked")
@Repository
@Log4j2
public class RatingDaoImpl implements RatingDao {

	@Autowired private SessionFactory sessionFactory;

	@Override
	public void saveRatings(Ratings ratings) {
		if (null != ratings) {
			Session session = sessionFactory.openSession();
			try {
				session.save(ratings);
				log.info("Ratings saved in database.");
			} catch (Exception e) {
				log.error(Constants.ERROR, e);
			} finally {
				session.close();
			}
		} else {
			log.info("Ratings cannot be null");
		}
	}

	@Override
	public void updateRatings(Ratings ratings) {
		if (null != ratings) {
			Session session = sessionFactory.openSession();
			try {
				Transaction txn = session.beginTransaction();
				session.update(ratings);
				txn.commit();
				log.info("Ratings updated in database.");
			} catch (Exception e) {
				log.error(Constants.ERROR, e);
			} finally {
				session.close();
			}
		} else {
			log.info("Ratings cannot be null");
		}
	}

	@Override
	public Ratings getRatingByIdAndProdName(Integer patientId, String productName) {
		if (null == patientId && null == productName || productName.isEmpty()) {
			return null;
		}
		Session session = sessionFactory.openSession();
		try {
			Query<Ratings> query = session
					.createQuery("from Ratings where PATIENT_ID=:patientId and PRODUCT_NAME=:productName");
			query.setParameter("patientId", patientId);
			query.setParameter("productName", productName);
			return query.getSingleResult();
		} catch (NoResultException e) {
			log.info(e.getMessage());
		} catch (Exception e) {
			log.error(Constants.ERROR_MESSAGE, e);
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Ratings> fetchAllByProductName(String productName) {
		if (null == productName || productName.isEmpty()) {
			return null;
		}
		Session session = sessionFactory.openSession();
		try {
			Query<Ratings> query = session.createQuery("from Ratings where PRODUCT_NAME=:productName");
			query.setParameter("productName", productName);
			return query.getResultList();
		} catch (NoResultException e) {
			log.info(e.getMessage());
		} catch (Exception e) {
			log.error(Constants.ERROR_MESSAGE, e);
		} finally {
			session.close();
		}
		return null;
	}

}
