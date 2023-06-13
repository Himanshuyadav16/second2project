package com.lise;

import com.lise.utils.ApplicationProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;


import static io.restassured.RestAssured.given;

public class BaseClass {
   public  String  accessToken= ApplicationProperties.INSTANCE.getToken();

    @BeforeSuite
      public  void beforeGetUser()
        {
             RestAssured.baseURI = ApplicationProperties.INSTANCE.getUrl();

            System.out.println("Before Suite");
        }


    //User Post Method
    public Response postUsers(String userBody){
        Response response= given()
                .header("Authorization",accessToken)
                .contentType(ContentType.JSON )
                .body(userBody)
                .request(Method.POST,"/users");
        return  response;
    }


    //Post Posts Method
    public Response postPost( String postPostBody,int id){
        Response response = given()
                .header("Authorization",accessToken)
                .contentType(ContentType.JSON)
                .body(postPostBody)
                .when()
                .request(Method.POST, "/users/"+id+"/posts");

        return response;
    }
}
