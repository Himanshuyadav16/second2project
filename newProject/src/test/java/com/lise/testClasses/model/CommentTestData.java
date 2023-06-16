package com.lise.testClasses.model;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.comments.CommentPostBody;
import com.lise.modals.comments.CommentPutBody;
import com.lise.modals.comments.CommentResponse;
import com.lise.modals.posts.PostPostBody;
import com.lise.modals.posts.PostResponse;
import com.lise.modals.users.UserPostBody;
import com.lise.modals.users.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommentTestData extends BaseClass {
    @Test
    public void getCommentTestData() {
        CommentResponse[] commentResponse = getComments();

        assertThat(commentResponse[0].getId(), is(notNullValue()));
        assertThat(commentResponse[0].getPost_id(), is(notNullValue()));
        assertThat(commentResponse[0].getName(), is(notNullValue()));
        assertThat(commentResponse[0].getBody(), is(notNullValue()));
        assertThat(commentResponse[0].getEmail(), is(notNullValue()));
    }

    @Test
    public void postCommentTestData() {
        Faker faker = new Faker();
        UserPostBody userPostBody = new UserPostBody();
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

        PostPostBody postPostBody = new PostPostBody();
        postPostBody.setUser_id(userId);
        postPostBody.setTitle("Et tam  curso certe denique tristis.");
        postPostBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        PostResponse postResponse = postPosts(postPostBody, userId);

        int postId = postResponse.getId();

        assertThat(postResponse.getId(), notNullValue());
        assertThat(postResponse.getUser_id(), is(postPostBody.user_id));
        assertThat(postResponse.getTitle(), is(postPostBody.title));
        assertThat(postResponse.getBody(), is(postPostBody.body));

        CommentPostBody commentPostBody = new CommentPostBody();
        commentPostBody.setPost_id(postId);
        commentPostBody.setName(faker.name().name());
        commentPostBody.setEmail(faker.internet().emailAddress());
        commentPostBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        CommentResponse responsePostComment = postComments(commentPostBody, postId);

        int commentId = responsePostComment.getId();

        assertThat(responsePostComment.getId(), notNullValue());
        assertThat(responsePostComment.getPost_id(), is(postId));
        assertThat(responsePostComment.getName(), is(commentPostBody.name));
        assertThat(responsePostComment.getEmail(), is(commentPostBody.email));
        assertThat(responsePostComment.getBody(), is(commentPostBody.body));

        Response deleteCommentResponse = deleteComment(commentId);

        Response deletePostResponse = deletePosts(postId);

        Response deleteUserResponse = deleteUsers(userId);


    }

    @Test
    public void putCommentTest() {
        Faker faker = new Faker();
        UserPostBody userPostBody = new UserPostBody();
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

        PostPostBody postPostBody = new PostPostBody();
        postPostBody.setUser_id(userId);
        postPostBody.setTitle("Et tam  curso certe denique tristis.");
        postPostBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        PostResponse postResponse = postPosts(postPostBody, userId);

        int postId = postResponse.getId();

        assertThat(postResponse.getId(), notNullValue());
        assertThat(postResponse.getUser_id(), is(postPostBody.user_id));
        assertThat(postResponse.getTitle(), is(postPostBody.title));
        assertThat(postResponse.getBody(), is(postPostBody.body));

        CommentPostBody commentPostBody = new CommentPostBody();
        commentPostBody.setPost_id(postId);
        commentPostBody.setName(faker.name().name());
        commentPostBody.setEmail(faker.internet().emailAddress());
        commentPostBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        CommentResponse responsePostComment = postComments(commentPostBody, postId);

        int commentId = responsePostComment.getId();

        assertThat(responsePostComment.getId(), notNullValue());
        assertThat(responsePostComment.getPost_id(), is(postId));
        assertThat(responsePostComment.getName(), is(commentPostBody.name));
        assertThat(responsePostComment.getEmail(), is(commentPostBody.email));
        assertThat(responsePostComment.getBody(), is(commentPostBody.body));

        CommentPutBody commentPutBody = new CommentPutBody();
        commentPutBody.setPost_id(postId);
        commentPutBody.setName(faker.name().name());
        commentPutBody.setEmail(faker.internet().emailAddress());
        commentPutBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        CommentResponse putCommentResponse = putComments(commentPutBody, commentId);

        int idCommentPut = putCommentResponse.getId();
        assertThat(putCommentResponse.getId(), is(idCommentPut));
        assertThat(putCommentResponse.getPost_id(), is(postId));
        assertThat(putCommentResponse.getName(), is(commentPutBody.name));
        assertThat(putCommentResponse.getEmail(), is(commentPutBody.email));
        assertThat(putCommentResponse.getBody(), is(commentPutBody.body));

        Response deletePutCommentResponse = deleteComment(idCommentPut);

        assertThat(deletePutCommentResponse.getStatusCode(), is(HttpStatus.SC_NO_CONTENT));

        Response deleteCommentResponse = deleteComment(commentId);

        Response deletePostResponse = deletePosts(postId);

        Response deleteUserResponse = deleteUsers(userId);

    }

    // Get Comment Method
    public CommentResponse[] getComments() {
        CommentResponse[] response = given()
                .request(Method.GET, "/comments")
                .then()
                .extract()
                .as(CommentResponse[].class);
        return response;
    }

    //Post Comment Method
    public CommentResponse postComments(CommentPostBody body, int id) {
        CommentResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts/" + id + "/comments")
                .then()
                .extract()
                .as(CommentResponse.class);
        return response;
    }

    // Put Comment Method
    public CommentResponse putComments(CommentPutBody body, int id) {
        CommentResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/comments/" + id)
                .then()
                .extract()
                .as(CommentResponse.class);
        return response;
    }
}
