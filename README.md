# DayToDay

DayToDay Health Assignment (Rating System)

Problem statement
 	    To Develop Rating feature for 5 medical products -  Lab Test Order, Mindfulness, CarePlan, WellBeing, and COVID-19

Assumptions:  
1.	Each Patient will have unique Id.  
2.	Rating will be in the range of 1 to 5
3.	Add API will only add rating, for updating different API is defined
4.	MySql is running on local, username and password is configurable in properties file

Approaches:
I have created a separate table to store Rating of particular product and linked it with patient Id.
 If a Patient is making request for either adding or updating the rating – first database is checked for any data with patient Id and product Name exists or not. If it exists, will update otherwise create new entry.
To fetch Rating, I am taking Product name as request and fetching the data from database to return the result.


  Database

• Ratings

	Column	        		DataType	             		 Description

	RATING_ID	      	   	Integer	                	Unique identifier of the table

	PATIENT_ID	   	        Integer	                Unique Identifier for the Patient who is giving rating

	PRODUCT_NAME	  		String	                Name of the Product for which rating is given (Lab Test Order, Mindfulness, CarePlan, WellBeing, and COVID-19)

	RATING	        		Integer	                Rating given for the product

	CREATED_ON	  	      Timestamp	                Automatic generated date at the time of creation

	UPDATED_ON	              Timestamp	                Automatic generated last modified date


Technology

Database – MySql

Language – JAVA

Framework – Spring, Hibernate


API Endpoints

         URL                         	   Method	         PayLoad	          	Success Response
									
	${base_ip}/v1/rating/add		  POST	      	 1.	patientId  	{
                                                      	      	 2.	productName
                                                      	      	 3.	rating	              "message": "Rating saved",
                                                                             			"status": "Success",
                                                                             			"data": null
                                                                          		}
                                                           
																																																											

	${base_ip}/v1/rating/update	           PUT	        1.	patientId         {
                                                  		2.	productName
                                                  		3.	rating	                 "message": "Rating Updaed",
                                                                              			 "status": "Success",
                                                                             			  "data": null
                                                                           		   }


	${base_ip}/v1/rating/fetch/productName	   GET	                              	   {
                                                                             			"message": "Rating Fetched",
                                                                               			"status": "Success",
                                                                                		 "data": {
                                                                                 		   "productName": "Mindfulness",
                                                                                  		    "averageRating": 3,
                                                                                   		    "totalRating": 3,
                                                                                    		    "individualRating": {
                                                                                                        "3": 1,
                                                                                                        "4": 2
                                                                                                        	      }
                                                                                         		 }
                                                                            		     }


