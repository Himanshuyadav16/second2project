package com.lise.testClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.UserGetResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class UserTestData extends BaseClass {
    @Test
    public void getUserTestData() throws JsonProcessingException {
        UserGetResponse[] getData = getUser();

        assertThat(getData[0].getId(), notNullValue());
        assertThat(getData[0].getName(), notNullValue());
        assertThat(getData[0].getEmail(), notNullValue());
        assertThat(getData[0].getGender(), notNullValue());
        assertThat(getData[0].getStatus(), notNullValue());
    }

    @Test
    public void postUserTestData() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender = "male";
        String userStatus = "active";

        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"" + userGender + "\",\n" +
                "\"status\":\"" + userStatus + "\"\n" +
                "}";
        UserGetResponse postUserResponse = postUser(userBody);
        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), notNullValue());
        assertThat(postUserResponse.getName(), is(userName));
        assertThat(postUserResponse.getEmail(), is(userEmail));
        assertThat(postUserResponse.getGender(), is(userGender));
        assertThat(postUserResponse.getStatus(), is(userStatus));
    }

    @Test
    public void putUserTestData() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender = "male";
        String userStatus = "active";
        String updatedUserName = faker.name().name();
        String updateUserEmail = faker.internet().emailAddress();
        String updateUserGender = "male";
        String updateUserStatus = "active";

        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"" + userGender + "\",\n" +
                "\"status\":\"" + userStatus + "\"\n" +
                "}";

        String updateUserBody = "  {\n" +
                "    \"email\":\"" + updateUserEmail + "\",\n" +
                "    \"name\":\"" + updatedUserName + "\",\n" +
                "    \"gender\": \"" + updateUserGender + "\",\n" +
                "    \"status\":\"" + updateUserStatus + "\"\n" +
                "}";
        UserGetResponse postUserResponse = postUser(userBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), notNullValue());
        assertThat(postUserResponse.getName(), is(userName));
        assertThat(postUserResponse.getEmail(), is(userEmail));
        assertThat(postUserResponse.getGender(), is(userGender));
        assertThat(postUserResponse.getStatus(), is(userStatus));

        UserGetResponse putUserResponse = putUser(updateUserBody, userId);

        int putUserId = putUserResponse.getId();

        assertThat(putUserResponse.getId(), is(putUserId));
        assertThat(putUserResponse.getName(), is(updatedUserName));
        assertThat(putUserResponse.getEmail(), is(updateUserEmail));
        assertThat(putUserResponse.getGender(), is(updateUserGender));
        assertThat(putUserResponse.getStatus(), is(updateUserStatus));
    }

    //User Get Method
    public UserGetResponse[] getUser() {
        UserGetResponse[] response = given()
                .contentType(ContentType.JSON)
                .request(Method.GET, "/users")
                .then()
                .extract()
                .response()
                .as(UserGetResponse[].class);
        return response;
    }

    // User Put Method
    public UserGetResponse putUser(String updateUserBody, int id) {
        UserGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .request(Method.PUT, "/users/" + id)
                .then()
                .extract()
                .as(UserGetResponse.class);
        return response;
    }

}
