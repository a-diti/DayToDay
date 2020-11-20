package com.day.today.health.utils;

import lombok.Getter;

public interface Constants {
	String SUCCESS = "Success";
	String FAILURE = "Failure";
	String ERROR = "Error";
	String ERROR_MESSAGE = "An Error Occured";

	@Getter
	public enum Product {
		LAB_TEST("Lab Test Order"), MINDFULNESS("Mindfulness"), CARE_PLAN("CarePlan"), WELL_BEING("WellBeing"),
		COVID_19("COVID-19");

		private String name;

		private Product(String name) {
			this.name = name;
		}

		public static Boolean validateProduct(String product) {
			if (null == product || product.isEmpty()) {
				return Boolean.FALSE;
			}
			for (Product ele : Product.values()) {
				if (ele.getName().equalsIgnoreCase(product)) {
					return Boolean.TRUE;
				}
			}
			return Boolean.FALSE;
		}
	}
}

