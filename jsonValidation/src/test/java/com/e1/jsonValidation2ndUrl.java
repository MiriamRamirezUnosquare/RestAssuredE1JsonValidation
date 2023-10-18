package com.e1;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class jsonValidation2ndUrl {
	@Test(priority = 1)
	  public void approachHttp() {
		  
		  // Approach: declaring the http objects 	  
		  RestAssured.baseURI = "https://reqres.in/api/";
		  RequestSpecification httpRequest = RestAssured.given();
		  Response response = httpRequest.get("/unknown/2");
			
		  int statusCode = response.getStatusCode();

		  // Assert that correct status code is returned.	  
		  Assert.assertEquals(statusCode,200);
		  Reporter.log("Sucess 200 validation");
		
		  // Assert all the items 	  
		  response.then().body("data.name", equalTo("fuchsia rose"));
		  response.then().body("data.year", equalTo(2001));
		  response.then().body("data.color", equalTo("#C74375"));
		  response.then().body("data.pantone_value", equalTo("17-2031"));
		  response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		  response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		  Reporter.log(response.body().asString());
		  
	  }
	  
	  @Test(priority = 2)
	  public void approachGivenWhenThen() {
		  // Approach: using Given().When().Then()	
		  
		  // Assert that correct status code is returned. Assert all the items
		  String response = 
		  given()
		  .when()
		  .get("https://reqres.in/api/unknown/2")
		  .then()
		  .assertThat().log().all().statusCode(200)
		  .assertThat().contentType(ContentType.JSON)
		  .assertThat().log().all().body("data.name", equalTo("fuchsia rose"))
		  .assertThat().log().all().body("data.year", equalTo(2001))
		  .assertThat().log().all().body("data.color", equalTo("#C74375"))
		  .assertThat().log().all().body("data.pantone_value", equalTo("17-2031"))
		  .assertThat().log().all().body("support.url", equalTo("https://reqres.in/#support-heading"))
		  .assertThat().log().all().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"))
		  .extract().response().asString();
		  
		  Reporter.log("Sucess 200 validation");
		  Reporter.log(response);	  
		  
	  }
}
