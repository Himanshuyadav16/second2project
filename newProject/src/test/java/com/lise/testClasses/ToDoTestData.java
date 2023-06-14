package com.lise.testClasses;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.ToDoGetResponse;
import com.lise.modals.UserGetResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ToDoTestData extends BaseClass {

    @Test
    public void getToDoTestData() {
        ToDoGetResponse[] toDoResponse = getToDos();

        assertThat(toDoResponse[0].getId(), is(notNullValue()));
        assertThat(toDoResponse[0].getUser_id(), is(notNullValue()));
        assertThat(toDoResponse[0].getTitle(), is(notNullValue()));
        assertThat(toDoResponse[0].getDue_on(), is(notNullValue()));
        assertThat(toDoResponse[0].getStatus(), is(notNullValue()));
    }

    @Test
    public void postToDoTestData() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender = "male";
        String userStatus = "active";
        String postTitle = "Capitulus adeo illo aurum consuasor.";
        String postDue_on = "2023-07-07T00:00:00.000+05:30";
        String postStatus = "completed";
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"" + userGender + "\",\n" +
                "\"status\":\"" + userStatus + "\"\n" +
                "}";
        UserGetResponse postUserResponse = postUser(userBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), is(notNullValue()));
        assertThat(postUserResponse.getName(), is(userName));
        assertThat(postUserResponse.getEmail(), is(userEmail));
        assertThat(postUserResponse.getGender(), is(userGender));
        assertThat(postUserResponse.getStatus(), is(userStatus));

        String postBody = "{\n" +
                "        \n" +
                "        \"user_id\": " + userId + ",\n" +
                "        \"title\": \"" + postTitle + "\",\n" +
                "        \"due_on\": \"" + postDue_on + "\",\n" +
                "        \"status\": \"" + postStatus + "\"\n" +
                "    }";
        ToDoGetResponse toDoPostResponse = postToDos(postBody);

        int toDoId = toDoPostResponse.getId();

        assertThat(toDoPostResponse.getUser_id(), is(userId));
        assertThat(toDoPostResponse.getTitle(), is(postTitle));
        assertThat(toDoPostResponse.getDue_on(), is(postDue_on));
        assertThat(toDoPostResponse.getStatus(), is(postStatus));

    }

    @Test
    public void putToDoTest() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender = "male";
        String userStatus = "active";
        String postTitle = "Capitulus adeo illo aurum consuasor.";
        String postDue_on = "2023-07-07T00:00:00.000+05:30";
        String postStatus = "completed";
        String putTitle = "Capitulus adeo illo aurum consuasor.";
        String putDue_on = "2023-07-07T00:00:00.000+05:30";
        String putStatus = "completed";
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"" + userGender + "\",\n" +
                "\"status\":\"" + userStatus + "\"\n" +
                "}";
        UserGetResponse postUserResponse = postUser(userBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), is(notNullValue()));
        assertThat(postUserResponse.getName(), is(userName));
        assertThat(postUserResponse.getEmail(), is(userEmail));
        assertThat(postUserResponse.getGender(), is(userGender));
        assertThat(postUserResponse.getStatus(), is(userStatus));

        String postBody = "{\n" +
                "        \n" +
                "        \"user_id\": " + userId + ",\n" +
                "        \"title\": \"" + postTitle + "\",\n" +
                "        \"due_on\": \"" + postDue_on + "\",\n" +
                "        \"status\": \"" + postStatus + "\"\n" +
                "    }";
        ToDoGetResponse toDoPostResponse = postToDos(postBody);

        int toDoId = toDoPostResponse.getId();

        assertThat(toDoPostResponse.getUser_id(), is(userId));
        assertThat(toDoPostResponse.getTitle(), is(postTitle));
        assertThat(toDoPostResponse.getDue_on(), is(postDue_on));
        assertThat(toDoPostResponse.getStatus(), is(postStatus));
        String putBody = "{\n" +
                "        \n" +
                "        \"user_id\": " + userId + ",\n" +
                "        \"title\": \"" + putTitle + "\",\n" +
                "        \"due_on\": \"" + putDue_on + "\",\n" +
                "        \"status\": \"" + putStatus + "\"\n" +
                "    }";
        ToDoGetResponse toDoPutResponse = putToDo(putBody, toDoId);

        int toDoPutId = toDoPutResponse.getId();

        assertThat(toDoPutResponse.getUser_id(), is(userId));
        assertThat(toDoPutResponse.getTitle(), is(postTitle));
        assertThat(toDoPutResponse.getDue_on(), is(postDue_on));
        assertThat(toDoPutResponse.getStatus(), is(postStatus));

    }

    //Get ToDos Method
    public ToDoGetResponse[] getToDos() {
        ToDoGetResponse[] response = given()
                .request(Method.GET, "/todos")
                .then()
                .extract()
                .as(ToDoGetResponse[].class);
        return response;
    }

    //Post ToDOs Method
    public ToDoGetResponse postToDos(String body) {
        ToDoGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/todos")
                .then()
                .extract()
                .as(ToDoGetResponse.class);
        return response;
    }

    //Put ToDos Method
    public ToDoGetResponse putToDo(String body, int id) {
        ToDoGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/todos/" + id)
                .then()
                .extract()
                .as(ToDoGetResponse.class);
        return response;
    }

}
