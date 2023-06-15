package com.lise.testClasses.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.lise.BaseClass;

import com.lise.modals.UserPostBody;
import com.lise.modals.UserPutBody;
import com.lise.modals.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import io.restassured.response.Response;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class UserTestData extends BaseClass {
    @Test
    public void getUserTestData() throws JsonProcessingException {
        UserResponse[] getData = getUser();

        assertThat(getData[0].getId(), notNullValue());
        assertThat(getData[0].getName(), notNullValue());
        assertThat(getData[0].getEmail(), notNullValue());
        assertThat(getData[0].getGender(), notNullValue());
        assertThat(getData[0].getStatus(), notNullValue());
    }

    @Test
    public void postUserTestData() {
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());
        userPostBody.setGender("male");
        userPostBody.setStatus("active");
        UserResponse postUserResponse = postUser(userPostBody);
        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), notNullValue());
        assertThat(postUserResponse.getName(), is(userPostBody.name));
        assertThat(postUserResponse.getEmail(), is(userPostBody.email));
        assertThat(postUserResponse.getGender(), is(userPostBody.gender));
        assertThat(postUserResponse.getStatus(), is(userPostBody.status));

        Response deleteUserResponse = deleteUsers(userId);
    }

    @Test
    public void putUserTestData() {
        Faker faker = new Faker();

        UserPostBody userPostBody =new UserPostBody();

        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());
        userPostBody.setGender("male");
        userPostBody.setStatus("active");
        UserResponse postUserResponse = postUser(userPostBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), notNullValue());
        assertThat(postUserResponse.getName(), is(userPostBody.name));
        assertThat(postUserResponse.getEmail(), is(userPostBody.email));
        assertThat(postUserResponse.getGender(), is(userPostBody.gender));
        assertThat(postUserResponse.getStatus(), is(userPostBody.status));

        UserPutBody updateUserBody =new UserPutBody();
        updateUserBody.setName(faker.name().name());
        updateUserBody.setEmail(faker.internet().emailAddress());
        updateUserBody.setGender("male");
        updateUserBody.setStatus("active");


        UserPostBody putUserResponse = putUser(updateUserBody, userId);

        int putUserId = putUserResponse.getId();

        assertThat(putUserResponse.getId(), is(putUserId));
        assertThat(putUserResponse.getName(), is());
        assertThat(putUserResponse.getEmail(), is(updateUserEmail));
        assertThat(putUserResponse.getGender(), is(updateUserGender));
        assertThat(putUserResponse.getStatus(), is(updateUserStatus));

        Response deletePostUserResponse = deleteUsers(putUserId);

        Response deleteUserResponse = deleteUsers(userId);

    }

    //User Get Method
    public UserResponse[] getUser() {
        UserResponse[] response = given()
                .contentType(ContentType.JSON)
                .request(Method.GET, "/users")
                .then()
                .extract()
                .response()
                .as(UserResponse[].class);
        return response;
    }
    // User Put Method
    public UserResponse putUser(UserPostBody updateUserBody, int id) {
        UserResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .request(Method.PUT, "/users/" + id)
                .then()
                .extract()
                .as(UserResponse.class);
        return response;
    }

}
