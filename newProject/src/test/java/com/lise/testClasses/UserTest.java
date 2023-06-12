package com.lise.testClasses;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;


public  class UserTest extends BaseClass {

    @Test
    public void getUserTest() {

         Response userResponse=getUser();

         assertThat(userResponse.getStatusCode(),is(HttpStatus.SC_OK));

         JSONArray jsonArrayUser=new JSONArray(userResponse.asString());

         assertThat(jsonArrayUser.length(),greaterThan(0));

        JSONObject jsonObjectUser=jsonArrayUser.getJSONObject(0);

        assertThat(jsonObjectUser.getInt("id"),is(notNullValue()));
        assertThat(jsonObjectUser.getString("name"),is(notNullValue()));
        assertThat(jsonObjectUser.getString("gender"),is(notNullValue()));
        assertThat(jsonObjectUser.getString("email"),is(notNullValue()));
        assertThat(jsonObjectUser.getString("status"),is(notNullValue()));
    }
    @Test
    public void postUserTest() {

        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender="male";
        String userStatus="active";

        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\""+userGender+"\",\n" +
                "\"status\":\""+userStatus+"\"\n" +
                "}";

        Response postUserResponse = postUsers(userBody);

         assertThat(postUserResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonObjectPost= new JSONObject(postUserResponse.asString());

        int idUser=jsonObjectPost.getInt("id");
        assertThat(jsonObjectPost.getInt("id"),is(notNullValue()));
        assertThat(jsonObjectPost.getString("name"),equalTo(userName));
        assertThat(jsonObjectPost.getString("email"),equalTo(userEmail));
        assertThat(jsonObjectPost.getString("gender"),equalTo(userGender));
        assertThat(jsonObjectPost.getString("status"),equalTo(userStatus));

        Response deleteUserResponse=deleteUsers(idUser);

        assertThat(deleteUserResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));


    }

    @Test
    public void putUserTest(){
        Faker faker = new Faker();
        String userName=faker.name().name();
        String userEmail=faker.internet().emailAddress();
        String userGender="male";
        String userStatus="active";
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\""+userGender+"\",\n" +
                "\"status\":\""+userStatus+"\"\n" +
                "}";
        Response postUserResponse = postUsers(userBody);
        assertThat(postUserResponse.getStatusCode(), is(HttpStatus.SC_CREATED));
        JSONObject jsonObjectPost= new JSONObject(postUserResponse.asString());

        int idUser=jsonObjectPost.getInt("id");
        assertThat(jsonObjectPost.getInt("id"),is(notNullValue()));
        assertThat(jsonObjectPost.getString("name"),equalTo(userName));
        assertThat(jsonObjectPost.getString("email"),equalTo(userEmail));
        assertThat(jsonObjectPost.getString("gender"),equalTo(userGender));
        assertThat(jsonObjectPost.getString("status"),equalTo(userStatus));

        String updatedUserName=faker.name().name();
        String updateUserEmail=faker.internet().emailAddress();
        String updateUserGender="male";
        String updateUserStatus="active";


        String updateUserBody="  {\n" +
                "    \"email\":\""+updateUserEmail+"\",\n" +
                "    \"name\":\""+updatedUserName+"\",\n" +
                "    \"gender\": \""+updateUserGender+"\",\n" +
                "    \"status\":\""+updateUserStatus+"\"\n" +
                "}";
        Response putUserResponse=putUser(updateUserBody,idUser);
        assertThat(putUserResponse.getStatusCode(),is(HttpStatus.SC_OK));

        JSONObject jsonObjectPutUser=new JSONObject(putUserResponse.asString());

        int idPutUser=jsonObjectPutUser.getInt("id");

        assertThat(jsonObjectPutUser.getInt("id"),equalTo(idPutUser));
        assertThat(jsonObjectPutUser.getString("name"),equalTo(updatedUserName));
        assertThat(jsonObjectPutUser.getString("email"),equalTo(updateUserEmail));
        assertThat(jsonObjectPutUser.getString("gender"),equalTo(updateUserGender));
        assertThat(jsonObjectPutUser.getString("status"),equalTo(updateUserStatus));


        Response deleteUserResponse=deleteUsers(idPutUser);

        assertThat(deleteUserResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
    }

@Test
public void deleteUserTest(){
    Faker faker= new Faker();
    String userName =faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userGender="male";
    String userStatus="active";
    String userBody = "{\n " +
            "\"name\":\"" +userName + "\",\n" +
            "\"email\":\"" +userEmail + "\",\n" +
            "\"gender\":\""+userGender+"\",\n" +
            "\"status\":\""+userStatus+"\"\n" +
            "}";
    Response postUserResponse = postUsers(userBody);


    assertThat(postUserResponse.getStatusCode(), is(HttpStatus.SC_CREATED));


    JSONObject jsonObjectPost= new JSONObject(postUserResponse.asString());

    int idUser=jsonObjectPost.getInt("id");
    assertThat(jsonObjectPost.getInt("id"),is(notNullValue()));
    assertThat(jsonObjectPost.getString("name"),equalTo(userName));
    assertThat(jsonObjectPost.getString("email"),equalTo(userEmail));
    assertThat(jsonObjectPost.getString("gender"),equalTo(userGender));
    assertThat(jsonObjectPost.getString("status"),equalTo(userStatus));


    Response deleteUserResponse=deleteUsers(idUser);

    assertThat(deleteUserResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));

}

    //User Get Method
    public Response getUser() {
        Response response= given()
                .request(Method.GET,"/users");
        return response;
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

    // User Put Method
    public Response putUser(String updateUserBody,int id){
        Response response=given()
                .header("Authorization",accessToken)
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .request(Method.PUT,"/users/"+id);
        return  response;

    }

    //  User Delete Method
    public Response deleteUsers(int id){
        Response response=given()
                .header("Authorization",accessToken)
                .request(Method.DELETE,"/users/"+id);
        return response;
    }

}