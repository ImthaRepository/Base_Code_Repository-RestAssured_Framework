package API_Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import API_Endpoints.User_EndPoints;
import API_Payloads.user_POJO;
import API_Utilities.Data_Providers;
import io.restassured.response.Response;

public class Data_Driven_Test {
	
		

	
	@Test(priority =1, dataProvider = "API Data", dataProviderClass = Data_Providers.class ) // since the data provider is avail in another class
	public void Test_Post_User(String userID, String username, String fname,String lname, String email, String password, String phone) {
        user_POJO payload=new user_POJO();
		
		payload.setId(Integer.parseInt(userID));  // conversion of userid from string format to integer
		payload.setUsername(username);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setUserStatus(0);
		Response response = User_EndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	
	@Test(priority =2, dataProvider = "Username Data", dataProviderClass = Data_Providers.class)
	public void Delete_UserByName(String username) {
		Response response = User_EndPoints.deleteUser(username);
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
}
