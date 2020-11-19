package com.day.today.health.data.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "RATING")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Ratings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2159032317729720475L;
		@Column(name = "RATING_ID")
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer ratingId;
		@Column(name = "PATIENT_ID", nullable = false)
		private Integer patientId;
		@Column(name = "PRODUCT_NAME", nullable = false)
		private String productName;
		@Column(name = "RATING", nullable = false)
		private Integer rating;
		@CreationTimestamp
		@Column(name = "CREATED_ON", nullable = false)
		private Timestamp createdOn;
		@UpdateTimestamp
		@Column(name = "UPDATED_ON", nullable = false)
		private Timestamp updatedOn;

	public Ratings(Integer patientId, String productName, Integer rating) {
		this.patientId = patientId;
		this.productName = productName;
		this.rating = rating;
	}
}
