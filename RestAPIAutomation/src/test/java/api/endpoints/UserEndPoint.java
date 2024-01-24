package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Use for only CRUD operation in the user modules

public class UserEndPoint {

	public static Response createUser(User payload)
	{
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
	        .post(endpoint.post_url); 
		
		return response;
		
	}
	
	public static Response readUser(String userName)
	{
		Response response=given()
		  .pathParam("username", userName)
		.when()
	      .get(endpoint.get_url); 
		
		return response;
		
	}
	

	public static Response updateUser(String userName, User payload)
	{
		Response response= given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .pathParam("username", userName)
		   .body(payload)
		.when()
	      .put(endpoint.update_url); 
		
		return response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		Response response= given()
		  .pathParam("username", userName)
		.when()
	      .delete(endpoint.delete_url); 
		
		return response;
		
	}
	
}
