package com.lise.testClasses.model;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.todos.ToDoPostBody;
import com.lise.modals.todos.ToDoPutBody;
import com.lise.modals.todos.ToDoResponse;
import com.lise.modals.users.UserPostBody;
import com.lise.modals.users.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ToDoTestData extends BaseClass {

    @Test
    public void getToDoTestData() {
        ToDoResponse[] toDoResponse = getToDos();

        assertThat(toDoResponse[0].getId(), is(notNullValue()));
        assertThat(toDoResponse[0].getUser_id(), is(notNullValue()));
        assertThat(toDoResponse[0].getTitle(), is(notNullValue()));
        assertThat(toDoResponse[0].getDue_on(), is(notNullValue()));
        assertThat(toDoResponse[0].getStatus(), is(notNullValue()));
    }

    @Test
    public void postToDoTestData() {
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

        ToDoPostBody toDoPostBody=new ToDoPostBody();
        toDoPostBody.setUser_id(userId);
        toDoPostBody.setTitle("Capitulus adeo illo aurum consuasor.");
        toDoPostBody.setDue_on("2023-07-07T00:00:00.000+05:30");
        toDoPostBody.setStatus("completed");

        ToDoResponse toDoPostResponse = postToDos(toDoPostBody);

        int toDoId = toDoPostResponse.getId();

        assertThat(toDoPostResponse.getId(), notNullValue());
        assertThat(toDoPostResponse.getUser_id(), is(toDoPostBody.user_id));
        assertThat(toDoPostResponse.getTitle(), is(toDoPostBody.title));
        assertThat(toDoPostResponse.getDue_on(), is(toDoPostBody.due_on));
        assertThat(toDoPostResponse.getStatus(), is(toDoPostBody.status));

        Response toDoDeleteResponse = deleteToDo(toDoId);

        Response deleteUserResponse = deleteUsers(userId);

    }

    @Test
    public void putToDoTest() {
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

        ToDoPostBody toDoPostBody=new ToDoPostBody();
        toDoPostBody.setUser_id(userId);
        toDoPostBody.setTitle("Capitulus adeo illo aurum consuasor.");
        toDoPostBody.setDue_on("2023-07-07T00:00:00.000+05:30");
        toDoPostBody.setStatus("completed");

        ToDoResponse toDoPostResponse = postToDos(toDoPostBody);

        int toDoId = toDoPostResponse.getId();

        assertThat(toDoPostResponse.getId(), notNullValue());
        assertThat(toDoPostResponse.getUser_id(), is(toDoPostBody.user_id));
        assertThat(toDoPostResponse.getTitle(), is(toDoPostBody.title));
        assertThat(toDoPostResponse.getDue_on(), is(toDoPostBody.due_on));
        assertThat(toDoPostResponse.getStatus(), is(toDoPostBody.status));

        ToDoPutBody toDoPutBody=new ToDoPutBody();
        toDoPutBody.setUser_id(userId);
        toDoPutBody.setTitle("Capitulus adeo illo aurum consuasor.");
        toDoPutBody.setDue_on("2023-07-07T00:00:00.000+05:30");
        toDoPutBody.setStatus("completed");

        ToDoResponse toDoPutResponse = putToDo(toDoPutBody, toDoId);

        int toDoPutId = toDoPutResponse.getId();

        assertThat(toDoPutResponse.getUser_id(), is(userId));
        assertThat(toDoPutResponse.getTitle(), is(toDoPutBody.title));
        assertThat(toDoPutResponse.getDue_on(), is(toDoPutBody.due_on));
        assertThat(toDoPutResponse.getStatus(), is(toDoPutBody.status));

        Response toDoPutDeleteResponse = deleteToDo(toDoPutId);

        Response toDoDeleteResponse = deleteToDo(toDoId);

        Response deleteUserResponse = deleteUsers(userId);
    }

    //Get ToDos Method
    public ToDoResponse[] getToDos() {
        ToDoResponse[] response = given()
                .request(Method.GET, "/todos")
                .then()
                .extract()
                .as(ToDoResponse[].class);
        return response;
    }

    //Post ToDOs Method
    public ToDoResponse postToDos(ToDoPostBody body) {
        ToDoResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/todos")
                .then()
                .extract()
                .as(ToDoResponse.class);
        return response;
    }

    //Put ToDos Method
    public ToDoResponse putToDo(ToDoPutBody body, int id) {
        ToDoResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/todos/" + id)
                .then()
                .extract()
                .as(ToDoResponse.class);
        return response;
    }
}
