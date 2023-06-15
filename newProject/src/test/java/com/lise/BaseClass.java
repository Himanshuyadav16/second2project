package com.lise;

import com.lise.modals.PostResponse;
import com.lise.modals.UserPostBody;
import com.lise.modals.UserResponse;
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
    public UserResponse postUser(UserPostBody userBody) {
        UserResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(userBody)
                .when()
                .request(Method.POST, "/users")
                .then()
                .extract()
                .as(UserResponse.class);
        return response;
    }

    //Post Posts Method
    public PostResponse postPosts(String postPostBody, int id) {
        PostResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(postPostBody)
                .when()
                .request(Method.POST, "/users/" + id + "/posts")
                .then()
                .extract()
                .as(PostResponse.class);
        return response;
    }

    //  User Delete Method
    public Response deleteUsers(int id) {
        Response response = given()
                .header("Authorization", accessToken)
                .request(Method.DELETE, "/users/" + id);
        return response;
    }

    //Post Delete Method
    public Response deletePosts(int id) {
        Response response = given()
                .header("Authorization", accessToken)
                .request(Method.DELETE, "/posts/" + id);
        return response;
    }

    //Delete Comment Method
    public Response deleteComment(int id) {
        Response response = given()
                .header("Authorization", accessToken)
                .request(Method.DELETE, "/comments/" + id);
        return response;
    }

    //Delete ToDos Method
    public Response deleteToDo(int id) {
        Response response = given()
                .header("Authorization", accessToken)
                .request(Method.DELETE, "/todos/" + id);
        return response;
    }
}
