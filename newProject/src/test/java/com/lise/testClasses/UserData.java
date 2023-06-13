package com.lise.testClasses;

import com.google.gson.Gson;
import com.lise.BaseClass;
import com.lise.modals.ResponseUserGet;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONArray;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


public class UserData extends BaseClass {

@Test
    public void getUserTestData() {
    List <ResponseUserGet> responseUserGets=new ArrayList<>();

        Response userResponse = getUser1();
    System.out.println(userResponse.asString());

    JSONArray jsonArray = new JSONArray(userResponse.asString());
    //for(int i=0;i<jsonArray.length();i++)
    {
        Gson gson = new Gson();
        ResponseUserGet user = gson.fromJson(jsonArray.getJSONObject(0).toString(), ResponseUserGet.class);
        responseUserGets.add(user);
    }
    System.out.println(responseUserGets);
    System.out.println(responseUserGets.get(0).getEmail());

    // System.out.println(userResponse.asString());
       assertThat(userResponse.getStatusCode(), is(HttpStatus.SC_OK));

//        assertThat(responseUserGets.getInt("id"), notNullValue());
//
//        assertThat(responseUserGets.getString("name"), notNullValue());
//
//        assertThat(responseUserGets.getString("gender"), notNullValue());
//
//        assertThat(responseUserGets.getString("email"), notNullValue());
//
//        assertThat(responseUserGets.getString("status"), notNullValue());
    }

    //User Get Method
    public Response getUser1() {
        ResponseUserGet response = given()
                .contentType(ContentType.JSON)
                .request(Method.GET, "/users")
                .then()
                .extract().response().as(ResponseUserGet.class);
        return response;
    }

}
