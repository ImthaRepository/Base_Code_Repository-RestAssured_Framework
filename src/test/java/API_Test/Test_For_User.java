package API_Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import API_Endpoints.User_EndPoints;
import API_Payloads.user_POJO;

import io.restassured.response.Response;

public class Test_For_User {

	Faker faker;
	user_POJO payload;
   public  Logger logger;
	
	@BeforeClass
	 public void set_Data() {
		faker= new Faker();
		payload=new user_POJO();
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		payload.setUserStatus(0);
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority =1)
	public void Test_Post_User() {
		logger.info("**********Creating User***********");
		Response response = User_EndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("********** User Created***********");
		logger.debug("Debugging*********");
	}
	
	@Test(priority =2)
	public void Get_UserByName() {
		Response response = User_EndPoints.ReadUser(this.payload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority =3)
	public void Test_Udate_User() {
		logger.info("**********Updating User***********");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = User_EndPoints.updateUser(this.payload.getUsername(),payload);
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("********** User updated***********");
		
		//check data after update
		
		Response responseAfterUpdate = User_EndPoints.ReadUser(this.payload.getUsername());
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
	}
	
	@Test(priority =4)
	public void Delete_UserByName() {
		logger.info("**********Deleting User***********");
		Response response = User_EndPoints.deleteUser(this.payload.getUsername());
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("********** User Deleted***********");
	}
	
	
		
	}
	
	

