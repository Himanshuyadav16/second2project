package com.lise;

import com.lise.modals.PostGetResponse;
import com.lise.modals.UserGetResponse;
import com.lise.utils.ApplicationProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class BaseClass {
    public String accessToken = ApplicationProperties.INSTANCE.getToken();

    @BeforeSuite
    public void beforeGetUser() {
        RestAssured.baseURI = ApplicationProperties.INSTANCE.getUrl();

        System.out.println("Before Suite");
    }


    //User Post Method
    public Response postUsers(String userBody) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(userBody)
                .request(Method.POST, "/users");
        return response;
    }


    //Post Posts Method
    public Response postPost(String postPostBody, int id) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(postPostBody)
                .when()
                .request(Method.POST, "/users/" + id + "/posts");

        return response;
    }


    //User Post Method
    public UserGetResponse postUser(String userBody) {
        UserGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(userBody)
                .request(Method.POST, "/users")
                .then()
                .extract()
                .as(UserGetResponse.class);
        return response;
    }

    //Post Posts Method
    public PostGetResponse postPosts(String postPostBody, int id) {
        PostGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(postPostBody)
                .when()
                .request(Method.POST, "/users/" + id + "/posts")
                .then()
                .extract()
                .as(PostGetResponse.class);
        return response;
    }

}
