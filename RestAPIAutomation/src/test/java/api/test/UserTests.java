package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayLoad;
	
	@BeforeClass
	public void setupData()
	{
		
		faker=new Faker();
		userPayLoad=new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5, 10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());		
		
	}
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		Response response= UserEndPoint.createUser(userPayLoad);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response= UserEndPoint.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		response.statusCode();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		//Update data using same payload	
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndPoint.updateUser(this.userPayLoad.getUsername(), userPayLoad);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data after updation
		Response responseAfterUpdate= UserEndPoint.readUser(this.userPayLoad.getUsername());
		responseAfterUpdate.then().log().all();
		responseAfterUpdate.statusCode();
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response= UserEndPoint.deleteUser(this.userPayLoad.getUsername());
		response.then().log().all();
		response.statusCode();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
}
