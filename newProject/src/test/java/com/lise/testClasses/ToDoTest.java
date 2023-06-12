package com.lise.testClasses;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import org.apache.http.HttpStatus;

public class ToDoTest extends BaseClass {
   @Test
   public void getToDoTest(){
       System.out.println("Get ToDos Test");
       Response responseToDo=getToDo();
       assertThat(responseToDo.getStatusCode(),is(HttpStatus.SC_OK));
       assertThat(responseToDo.asString(),is(notNullValue()));
       System.out.println(responseToDo.asString());
   }

   @Test
   public  void postToDoTest(){
       System.out.println("Post ToDos Method");
       Faker faker=new Faker();
       String userName=faker.name().name();
       String userEmail=faker.internet().emailAddress();
       String userBody = "{\n " +
               "\"name\":\"" + userName + "\",\n" +
               "\"email\":\"" + userEmail + "\",\n" +
               "\"gender\":\"male\",\n" +
               "\"status\":\"active\"\n" +
               "}";
       Response responseUser = postUsers(userBody);
       assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
       assertThat(responseUser.asString(), is(notNullValue()));

       JSONObject jsonObjectUser=new JSONObject(responseUser.asString());
       int idUser=jsonObjectUser.getInt("id");
       String postBody="{\n" +
               "        \n" +
               "        \"user_id\": "+idUser+",\n" +
               "        \"title\": \"Capitulus adeo illo aurum consuasor.\",\n" +
               "        \"due_on\": \"2023-07-07T00:00:00.000+05:30\",\n" +
               "        \"status\": \"completed\"\n" +
               "    }";
       Response responseToDoPost=postToDo(postBody);
       assertThat(responseToDoPost.getStatusCode(),is(HttpStatus.SC_CREATED));
       assertThat(responseToDoPost.asString(),is(notNullValue()));
       System.out.println("ToDos Response Post=>"+responseToDoPost.asString());
   }
@Test
   public void putToDoTest(){
    System.out.println("Put ToDos Method");
    Faker faker=new Faker();
    String userName=faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userBody = "{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responseUser = postUsers(userBody);
    assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responseUser.asString(), is(notNullValue()));

    JSONObject jsonObjectUser=new JSONObject(responseUser.asString());
    int idUser=jsonObjectUser.getInt("id");
    String postBody="{\n" +
            "        \n" +
            "        \"user_id\": "+idUser+",\n" +
            "        \"title\": \"Capitulus adeo illo aurum consuasor.\",\n" +
            "        \"due_on\": \"2023-07-07T00:00:00.000+05:30\",\n" +
            "        \"status\": \"completed\"\n" +
            "    }";
    Response responseToDoPost=postToDo(postBody);
    assertThat(responseToDoPost.getStatusCode(),is(HttpStatus.SC_CREATED));
    assertThat(responseToDoPost.asString(),is(notNullValue()));

   JSONObject jsonObjectToDo=new JSONObject(responseToDoPost.asString());
   int idTodo=jsonObjectToDo.getInt("id");
   String putBody="{\n" +
           "        \n" +
           "        \"user_id\": "+idUser+",\n" +
           "        \"title\": \"Capitulus adeo illo aurum consuasor.\",\n" +
           "        \"due_on\": \"2023-07-07T00:00:00.000+05:30\",\n" +
           "        \"status\": \"completed\"\n" +
           "    }";
   Response responseToDoPut=putToDO(putBody,idTodo);
   assertThat(responseToDoPut.getStatusCode(),is(HttpStatus.SC_OK));
   assertThat(responseToDoPut.asString(),is(notNullValue()));
    System.out.println("ToDos Response Put"+responseToDoPut.asString());
   }

@Test
public void deleteToDoTest(){
    System.out.println("Delete ToDos Method");
    Faker faker=new Faker();
    String userName=faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userBody = "{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responseUser = postUsers(userBody);
    assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responseUser.asString(), is(notNullValue()));

    JSONObject jsonObjectUser=new JSONObject(responseUser.asString());
    int idUser=jsonObjectUser.getInt("id");
    String postBody="{\n" +
            "        \n" +
            "        \"user_id\": "+idUser+",\n" +
            "        \"title\": \"Capitulus adeo illo aurum consuasor.\",\n" +
            "        \"due_on\": \"2023-07-07T00:00:00.000+05:30\",\n" +
            "        \"status\": \"completed\"\n" +
            "    }";
    Response responseToDoPost=postToDo(postBody);
    assertThat(responseToDoPost.getStatusCode(),is(HttpStatus.SC_CREATED));
    assertThat(responseToDoPost.asString(),is(notNullValue()));

    JSONObject jsonObjectToDo=new JSONObject(responseToDoPost.asString());
    int idTodo=jsonObjectToDo.getInt("id");
    Response responseToDoDelete=deleteToDo(idTodo);
    assertThat(responseToDoDelete.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
    assertThat(responseToDoDelete.asString(),is(notNullValue()));
    System.out.println("Todos Response Delete =>"+responseToDoDelete.asString());

}

//Get ToDos Method
 public Response getToDo() {
Response response=given()
        .request(Method.GET,"/todos");
return  response;
 }

    //Post ToDOs Method
    public Response postToDo(String body){
        Response response=given()
                .header("Authorization",accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST,"/todos");
        return  response;
    }

    //Put ToDos Method
    public Response putToDO(String body,int id){
        Response response=given()
                .header("Authorization",accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT,"/todos/"+id);
        return  response;
    }

//Delete ToDos Method

    public Response deleteToDo(int id){
        Response response=given()
                .header("Authorization",accessToken)
                .request(Method.DELETE,"/todos/"+id);
        return response;
    }
}