package com.lise.testClasses;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.Method;


public class PostTest extends BaseClass {

@Test
public void getPostTest() {

    Response postResponse=getPost();

    assertThat(postResponse.getStatusCode(),is(HttpStatus.SC_OK));

    JSONArray jsonArrayPost=new JSONArray(postResponse.asString());

    assertThat(jsonArrayPost.length(),greaterThan(0));

    JSONObject jsonObjectPost=jsonArrayPost.getJSONObject(0);

    assertThat(jsonObjectPost.getInt("id"),is(notNullValue()));
    assertThat(jsonObjectPost.getInt("user_id"),is(notNullValue()));
    assertThat(jsonObjectPost.getString("title"),is(notNullValue()));
    assertThat(jsonObjectPost.getString("body"),is(notNullValue()));

}

@Test
public void postPostsTest(){
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


    String postsTitle="Et tam  curso certe denique tristis.";
    String postsBody="Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.";

    String postBody=" { \n" +
            "    \"title\": \""+postsTitle+"\",\n" +
            "    \"body\":\""+postsBody+"\"\n" +
            "}";

    Response postResponse=postPost(postBody,idUser);

    assertThat(postResponse.getStatusCode(),is(HttpStatus.SC_CREATED));

    JSONObject jsonObjectPut=new JSONObject(postResponse.asString());

    int idPost=jsonObjectPut.getInt("id");

    assertThat(jsonObjectPut.getInt("id"),equalTo(idPost));
    assertThat(jsonObjectPut.getInt("user_id"),equalTo(idUser));
    assertThat(jsonObjectPut.getString("title"),equalTo(postsTitle));
    assertThat(jsonObjectPut.getString("body"),equalTo(postsBody));

    Response deletePostResponse=deletePosts(idPost);
    assertThat(deletePostResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
}

@Test
public void putPostTest(){
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


    String postsTitle="Et tam  certe denique istis.";
    String postsBody="Tenus vigor ut. Triduana praesentiu qui. Ab repellendus tertius. Copiose adultus sit.";

    String postBody=" { \n" +
            "    \"title\": \""+postsTitle+"\",\n" +
            "    \"body\":\""+postsBody+"\"\n" +
            "}";

    Response postResponse=postPost(postBody,idUser);

    assertThat(postResponse.getStatusCode(),is(HttpStatus.SC_CREATED));

    JSONObject jsonObjectPut=new JSONObject(postResponse.asString());

    int idPost=jsonObjectPut.getInt("id");

    assertThat(jsonObjectPut.getInt("id"),equalTo(idPost));
    assertThat(jsonObjectPut.getInt("user_id"),equalTo(idUser));
    assertThat(jsonObjectPut.getString("title"),equalTo(postsTitle));
    assertThat(jsonObjectPut.getString("body"),equalTo(postsBody));

    String putPostTitle="Et tam  certe denique istis.";
    String putPostBody="Tenus vigor ut. Triduana praesentiu qui. Ab repellendus tertius. Copiose adultus sit.";

    String putBody="{\n" +
            "    \"user_id\": "+idUser+",\n" +
            "    \"title\": \""+putPostTitle+"\",\n" +
            "    \"body\": \""+putPostBody+"\"\n" +
            "}";
    Response putResponse=putPosts(putBody,idPost);
    assertThat(putResponse.getStatusCode(),is(HttpStatus.SC_OK));

    JSONObject jsonObjectPutPost=new JSONObject(postResponse.asString());
    int idPut=jsonObjectPutPost.getInt("id");
    assertThat(jsonObjectPutPost.getString("title"),equalTo(putPostTitle));
    assertThat(jsonObjectPutPost.getString("body"),equalTo(putPostBody));

    Response deletePostResponse=deletePosts(idPut);
    assertThat(deletePostResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));


}
@Test
public void deletePostTest(){
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


    String postsTitle="Et tam  curso certe denique tristis.";
    String postsBody="Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.";

    String postBody=" { \n" +
            "    \"title\": \""+postsTitle+"\",\n" +
            "    \"body\":\""+postsBody+"\"\n" +
            "}";

    Response postResponse=postPost(postBody,idUser);

    assertThat(postResponse.getStatusCode(),is(HttpStatus.SC_CREATED));

    JSONObject jsonObjectPut=new JSONObject(postResponse.asString());

    int idPost=jsonObjectPut.getInt("id");

    assertThat(jsonObjectPut.getInt("id"),equalTo(idPost));
    assertThat(jsonObjectPut.getInt("user_id"),equalTo(idUser));
    assertThat(jsonObjectPut.getString("title"),equalTo(postsTitle));
    assertThat(jsonObjectPut.getString("body"),equalTo(postsBody));

    Response deletePostResponse=deletePosts(idPost);
    assertThat(deletePostResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
}

// Post Get Method
    public Response getPost(){
    Response response=given()
            .request(Method.GET,"/posts");
    return response;
    }



    // Put Post Method
    public Response putPosts(String body,int id){
        Response response=given()
                .header("Authorization",accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.PUT,"/posts/"+id);

        return response;
    }

    //Post Delete Method
    public Response deletePosts(int id){
        Response response=given()
                .header("Authorization",accessToken)
                .request(Method.DELETE,"/posts/"+id);
        return response;
    }

}
