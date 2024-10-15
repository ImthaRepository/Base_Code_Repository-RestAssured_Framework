package API_Endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import API_Payloads.user_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User_EndPoints {

	public static Response createUser(user_POJO payload)
	{
		Response response= given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return response;
	}
	
	public static Response ReadUser(String username)
	{
		Response response= given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String username, user_POJO payload)
	{
		Response response= given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String username)
	{
		Response response= given()
			.pathParam("username", username)
		.when()
			.delete(Routes.get_url);
		
		return response;
	}
	
	
	
}
