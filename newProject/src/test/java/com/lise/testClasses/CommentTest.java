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


public class CommentTest extends BaseClass {
    @Test
    public void getCommentTest() {
        Response commentResponse = getComment();

        assertThat(commentResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONArray jsonArrayComment=new JSONArray(commentResponse.asString());


        assertThat(jsonArrayComment.length(),greaterThan(0));

        JSONObject jsonObjectComment=jsonArrayComment.getJSONObject(0);

        assertThat(jsonObjectComment.getInt("id"),is(notNullValue()));
        assertThat(jsonObjectComment.getInt("post_id"),is(notNullValue()));
        assertThat(jsonObjectComment.getString("name"),is(notNullValue()));
        assertThat(jsonObjectComment.getString("body"),is(notNullValue()));
        assertThat(jsonObjectComment.getString("email"),is(notNullValue()));

    }

    @Test
    public void postCommentTest() {
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


        String postCommentName=faker.name().name();
        String postCommentEmail=faker.internet().emailAddress();
        String postCommentPostsBody="Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.";

        String postCommentBody = " {\n" +
                "        \"post_id\": " + idPost + ",\n" +
                "        \"name\": \""+postCommentName+"\",\n" +
                "        \"email\": \""+postCommentEmail+"\",\n" +
                "        \"body\": \""+postCommentPostsBody+"\"\n" +
                "    }";
        Response responsePostComment = postComment(postCommentBody, idPost);

        assertThat(responsePostComment.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonObjectComment=new JSONObject(responsePostComment.asString());
        int idComment=jsonObjectComment.getInt("id");
        assertThat(jsonObjectComment.getInt("id"),equalTo(idComment));
        assertThat(jsonObjectComment.getInt("post_id"),equalTo(idPost));
        assertThat(jsonObjectComment.getString("name"),equalTo(postCommentName));
        assertThat(jsonObjectComment.getString("email"),equalTo(postCommentEmail));
        assertThat(jsonObjectComment.getString("body"),equalTo(postCommentPostsBody));

        Response deleteCommentResponse=deleteComment(idComment);
        assertThat(deleteCommentResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));

    }

    @Test
    public void putCommentTest() {
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


        String postCommentName=faker.name().name();
        String postCommentEmail=faker.internet().emailAddress();
        String postCommentPostsBody="Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.";

        String postCommentBody = " {\n" +
                "        \"post_id\": " + idPost + ",\n" +
                "        \"name\": \""+postCommentName+"\",\n" +
                "        \"email\": \""+postCommentEmail+"\",\n" +
                "        \"body\": \""+postCommentPostsBody+"\"\n" +
                "    }";
        Response responsePostComment = postComment(postCommentBody, idPost);

        assertThat(responsePostComment.getStatusCode(), is(HttpStatus.SC_CREATED));

        JSONObject jsonObjectComment=new JSONObject(responsePostComment.asString());
        int idComment=jsonObjectComment.getInt("id");
        assertThat(jsonObjectComment.getInt("id"),equalTo(idComment));
        assertThat(jsonObjectComment.getInt("post_id"),equalTo(idPost));
        assertThat(jsonObjectComment.getString("name"),equalTo(postCommentName));
        assertThat(jsonObjectComment.getString("email"),equalTo(postCommentEmail));
        assertThat(jsonObjectComment.getString("body"),equalTo(postCommentPostsBody));

        String putCommentName=faker.name().name();
        String putCommentEmail=faker.internet().emailAddress();
        String putCommentPutBody="Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsu";

        String putCommentBody = " {\n" +
                "        \"post_id\": " + idPost + ",\n" +
                "        \"name\": \""+putCommentName+"\",\n" +
                "        \"email\": \""+putCommentEmail+"\",\n" +
                "        \"body\": \""+putCommentPutBody+"\"\n" +
                "    }";

        Response putCommentResponse = putComment(putCommentBody, idComment);

        assertThat(putCommentResponse.getStatusCode(), is(HttpStatus.SC_OK));

        JSONObject jsonObjectCommentPut=new JSONObject(putCommentResponse.asString());
        int idCommentPut=jsonObjectCommentPut.getInt("id");
        assertThat(jsonObjectCommentPut.getInt("id"),equalTo(idCommentPut));
        assertThat(jsonObjectCommentPut.getInt("post_id"),equalTo(idPost));
        assertThat(jsonObjectCommentPut.getString("name"),equalTo(putCommentName));
        assertThat(jsonObjectCommentPut.getString("email"),equalTo(putCommentEmail));
        assertThat(jsonObjectCommentPut.getString("body"),equalTo(putCommentPutBody));

        Response deleteCommentResponse=deleteComment(idCommentPut);
        assertThat(deleteCommentResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));


    }
@Test
public void  deleteCommentTest(){
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


    String postCommentName=faker.name().name();
    String postCommentEmail=faker.internet().emailAddress();
    String postCommentPostsBody="Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.";

    String postCommentBody = " {\n" +
            "        \"post_id\": " + idPost + ",\n" +
            "        \"name\": \""+postCommentName+"\",\n" +
            "        \"email\": \""+postCommentEmail+"\",\n" +
            "        \"body\": \""+postCommentPostsBody+"\"\n" +
            "    }";
    Response responsePostComment = postComment(postCommentBody, idPost);

    assertThat(responsePostComment.getStatusCode(), is(HttpStatus.SC_CREATED));

    JSONObject jsonObjectComment=new JSONObject(responsePostComment.asString());
    int idComment=jsonObjectComment.getInt("id");
    assertThat(jsonObjectComment.getInt("id"),equalTo(idComment));
    assertThat(jsonObjectComment.getInt("post_id"),equalTo(idPost));
    assertThat(jsonObjectComment.getString("name"),equalTo(postCommentName));
    assertThat(jsonObjectComment.getString("email"),equalTo(postCommentEmail));
    assertThat(jsonObjectComment.getString("body"),equalTo(postCommentPostsBody));

    JSONObject jsonObjectCommentDelete = new JSONObject(responsePostComment.asString());
    int postIdComment = jsonObjectCommentDelete.getInt("id");

     Response responseDeleteComment=deleteComment(postIdComment);
     assertThat(responseDeleteComment.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
}

    // Get Comment Method
    public Response getComment() {
        Response response = given()
                .request(Method.GET, "/comments");
        return response;
    }

    //Post Comment Method
    public Response postComment(String body, int postId) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts/" + postId + "/comments");

        return response;
    }

    // Put Comment Method
    public Response putComment(String body, int postIdComment) {
        Response response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/comments/" + postIdComment);
        return response;
    }


    //Delete Comment Method
    public Response deleteComment(int id) {
        Response response = given()
                .header("Authorization", accessToken)
                .request(Method.DELETE, "/comments/" + id);
        return response;
    }
}