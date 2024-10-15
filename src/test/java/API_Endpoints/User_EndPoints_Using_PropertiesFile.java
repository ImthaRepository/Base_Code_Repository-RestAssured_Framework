package API_Endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import API_Payloads.user_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User_EndPoints_Using_PropertiesFile {
	
	static ResourceBundle geturl(){
		ResourceBundle routes=ResourceBundle.getBundle("Routes");  // load property file
		return routes;
	}
	
	
	public static Response createUser(user_POJO payload)
	{
		String post_url=geturl().getString("post_url");
		Response response= given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return response;
	}
	
	public static Response ReadUser(String username) {
	String get_url=geturl().getString("get_url");
	
		Response response= given()
			.pathParam("username", username)
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String username, user_POJO payload)
	{
		String update_url=geturl().getString("update_url");
		Response response= given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(update_url);
		
		return response;
	}
	
	public static Response deleteUser(String username)
	{
		String delete_url=geturl().getString("Delete_url");
		Response response= given()
			.pathParam("username", username)
		.when()
			.delete(delete_url);
		
		return response;
	}
}
