package com.lise.testClasses;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.CommentGetResponse;
import com.lise.modals.PostGetResponse;
import com.lise.modals.UserGetResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommentTestData extends BaseClass {
    @Test
    public void getCommentTestData() {
        CommentGetResponse[] commentResponse = getComments();

        assertThat(commentResponse[0].getId(), is(notNullValue()));
        assertThat(commentResponse[0].getPost_id(), is(notNullValue()));
        assertThat(commentResponse[0].getName(), is(notNullValue()));
        assertThat(commentResponse[0].getBody(), is(notNullValue()));
        assertThat(commentResponse[0].getEmail(), is(notNullValue()));
    }

    @Test
    public void postCommentTestData() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender = "male";
        String userStatus = "active";
        String postsTitle = "Et tam  curso certe denique tristis.";
        String postsBody = "Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.";
        String postCommentName = faker.name().name();
        String postCommentEmail = faker.internet().emailAddress();
        String postCommentPostsBody = "Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.";
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"" + userGender + "\",\n" +
                "\"status\":\"" + userStatus + "\"\n" +
                "}";
        String postBody = " { \n" +
                "    \"title\": \"" + postsTitle + "\",\n" +
                "    \"body\":\"" + postsBody + "\"\n" +
                "}";
        UserGetResponse postUserResponse = postUser(userBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), is(notNullValue()));
        assertThat(postUserResponse.getName(), is(userName));
        assertThat(postUserResponse.getEmail(), is(userEmail));
        assertThat(postUserResponse.getGender(), is(userGender));
        assertThat(postUserResponse.getStatus(), is(userStatus));

        PostGetResponse postResponse = postPosts(postBody, userId);

        int postId = postResponse.getId();

        assertThat(postResponse.getId(), is(postId));
        assertThat(postResponse.getUser_id(), is(userId));
        assertThat(postResponse.getTitle(), is(postsTitle));
        assertThat(postResponse.getBody(), is(postsBody));

        String postCommentBody = " {\n" +
                "        \"post_id\": " + postId + ",\n" +
                "        \"name\": \"" + postCommentName + "\",\n" +
                "        \"email\": \"" + postCommentEmail + "\",\n" +
                "        \"body\": \"" + postCommentPostsBody + "\"\n" +
                "    }";
        CommentGetResponse responsePostComment = postComments(postCommentBody, postId);

        int commentId = responsePostComment.getId();

        assertThat(responsePostComment.getId(), is(commentId));
        assertThat(responsePostComment.getPost_id(), is(postId));
        assertThat(responsePostComment.getName(), is(postCommentName));
        assertThat(responsePostComment.getEmail(), is(postCommentEmail));
        assertThat(responsePostComment.getBody(), is(postCommentPostsBody));
    }

    @Test
    public void putCommentTest() {
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userGender = "male";
        String userStatus = "active";
        String postsTitle = "Et tam  curso certe denique tristis.";
        String postsBody = "Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.";
        String postCommentName = faker.name().name();
        String postCommentEmail = faker.internet().emailAddress();
        String postCommentPostsBody = "Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.";
        String putCommentName = faker.name().name();
        String putCommentEmail = faker.internet().emailAddress();
        String putCommentPutBody = "Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsu";
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"" + userGender + "\",\n" +
                "\"status\":\"" + userStatus + "\"\n" +
                "}";
        String postBody = " { \n" +
                "    \"title\": \"" + postsTitle + "\",\n" +
                "    \"body\":\"" + postsBody + "\"\n" +
                "}";
        UserGetResponse postUserResponse = postUser(userBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), is(notNullValue()));
        assertThat(postUserResponse.getName(), is(userName));
        assertThat(postUserResponse.getEmail(), is(userEmail));
        assertThat(postUserResponse.getGender(), is(userGender));
        assertThat(postUserResponse.getStatus(), is(userStatus));

        PostGetResponse postResponse = postPosts(postBody, userId);

        int postId = postResponse.getId();

        assertThat(postResponse.getId(), is(postId));
        assertThat(postResponse.getUser_id(), is(userId));
        assertThat(postResponse.getTitle(), is(postsTitle));
        assertThat(postResponse.getBody(), is(postsBody));

        String postCommentBody = " {\n" +
                "        \"post_id\": " + postId + ",\n" +
                "        \"name\": \"" + postCommentName + "\",\n" +
                "        \"email\": \"" + postCommentEmail + "\",\n" +
                "        \"body\": \"" + postCommentPostsBody + "\"\n" +
                "    }";
        CommentGetResponse responsePostComment = postComments(postCommentBody, postId);

        int commentId = responsePostComment.getId();

        assertThat(responsePostComment.getId(), is(commentId));
        assertThat(responsePostComment.getPost_id(), is(postId));
        assertThat(responsePostComment.getName(), is(postCommentName));
        assertThat(responsePostComment.getEmail(), is(postCommentEmail));
        assertThat(responsePostComment.getBody(), is(postCommentPostsBody));

        String putCommentBody = " {\n" +
                "        \"post_id\": " + postId + ",\n" +
                "        \"name\": \"" + putCommentName + "\",\n" +
                "        \"email\": \"" + putCommentEmail + "\",\n" +
                "        \"body\": \"" + putCommentPutBody + "\"\n" +
                "    }";
        CommentGetResponse putCommentResponse = putComments(putCommentBody, commentId);

        int idCommentPut = putCommentResponse.getId();
        assertThat(putCommentResponse.getId(), is(idCommentPut));
        assertThat(putCommentResponse.getPost_id(), is(postId));
        assertThat(putCommentResponse.getName(), is(putCommentName));
        assertThat(putCommentResponse.getEmail(), is(putCommentEmail));
        assertThat(putCommentResponse.getBody(), is(putCommentPutBody));
    }

    // Get Comment Method
    public CommentGetResponse[] getComments() {
        CommentGetResponse[] response = given()
                .request(Method.GET, "/comments")
                .then()
                .extract()
                .as(CommentGetResponse[].class);
        return response;
    }

    //Post Comment Method
    public CommentGetResponse postComments(String body, int id) {
        CommentGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts/" + id + "/comments")
                .then()
                .extract()
                .as(CommentGetResponse.class);
        return response;
    }

    // Put Comment Method
    public CommentGetResponse putComments(String body, int id) {
        CommentGetResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/comments/" + id)
                .then()
                .extract()
                .as(CommentGetResponse.class);
        return response;
    }
}
