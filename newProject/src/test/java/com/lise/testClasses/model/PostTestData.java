package com.lise.testClasses.model;

import com.github.javafaker.Faker;
import com.lise.BaseClass;
import com.lise.modals.posts.PostPostBody;
import com.lise.modals.posts.PostPutBody;
import com.lise.modals.posts.PostResponse;
import com.lise.modals.users.UserPostBody;
import com.lise.modals.users.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostTestData extends BaseClass {

    @Test
    public void getPostTestData() {
        PostResponse[] postResponse = getPosts();

        assertThat(postResponse[0].getId(), is(notNullValue()));
        assertThat(postResponse[0].getUser_id(), is(notNullValue()));
        assertThat(postResponse[0].getTitle(), is(notNullValue()));
        assertThat(postResponse[0].getBody(), is(notNullValue()));
    }

    @Test
    public void postPostsTestData() {
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

        PostPostBody postPostBody=new PostPostBody();
        postPostBody.setUser_id(userId);
        postPostBody.setTitle("Et tam  curso certe denique tristis.");
        postPostBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        PostResponse postResponse = postPosts(postPostBody, userId);

        int postId = postResponse.getId();

        assertThat(postResponse.getId(), notNullValue());
        assertThat(postResponse.getUser_id(), is(postPostBody.user_id));
        assertThat(postResponse.getTitle(), is(postPostBody.title));
        assertThat(postResponse.getBody(), is(postPostBody.body));

        Response deletePostResponse = deletePosts(postId);

        Response deleteUserResponse = deleteUsers(userId);
    }

    @Test
    public void putPostTestData() {
        Faker faker = new Faker();
        UserPostBody userPostBody =new UserPostBody();
        userPostBody.setName(faker.name().name());
        userPostBody.setEmail(faker.internet().emailAddress());
        userPostBody.setGender("male");
        userPostBody.setStatus("active");

        String putPostTitle = "Et tam  certe denique istis.";
        String putPostBody = "Tenus vigor ut. Triduana praesentiu qui. Ab repellendus tertius. Copiose adultus sit.";


        UserResponse postUserResponse = postUser(userPostBody);

        int userId = postUserResponse.getId();

        assertThat(postUserResponse.getId(), is(notNullValue()));
        assertThat(postUserResponse.getName(), is(userPostBody.name));
        assertThat(postUserResponse.getEmail(), is(userPostBody.email));
        assertThat(postUserResponse.getGender(), is(userPostBody.gender));
        assertThat(postUserResponse.getStatus(), is(userPostBody.status));
        PostPostBody postPostBody=new PostPostBody();
        postPostBody.setUser_id(userId);
        postPostBody.setTitle("Et tam  curso certe denique tristis.");
        postPostBody.setBody("Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        PostResponse postResponse = postPosts(postPostBody, userId);

        int postId = postResponse.getId();

        assertThat(postResponse.getId(), is(postId));
        assertThat(postResponse.getUser_id(), is(postPostBody.user_id));
        assertThat(postResponse.getTitle(), is(postPostBody.title));
        assertThat(postResponse.getBody(), is(postPostBody.body));

        PostPutBody postPutBody=new PostPutBody();

        postPutBody.setUser_id(userId);
        postPutBody.setTitle("Et tam  curso  denique tristis.");
        postPutBody.setBody("Tenus vigor ut.  praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.");

        PostResponse putResponse = putPost(postPutBody, postId);

        int putId = putResponse.getId();

        assertThat(putResponse.getId(), notNullValue());
        assertThat(putResponse.getUser_id(), is(postPutBody.user_id));
        assertThat(putResponse.getTitle(), is(postPutBody.title));
        assertThat(putResponse.getBody(), is(postPutBody.body));

        Response deletePutResponse = deletePosts(putId);

        Response deletePostResponse = deletePosts(postId);

        Response deleteUserResponse = deleteUsers(userId);
    }


    // Post Get Method
    public PostResponse[] getPosts() {
        PostResponse[] response = given()
                .request(Method.GET, "/posts")
                .then()
                .extract()
                .as(PostResponse[].class);
        return response;
    }


    // Put Post Method
    public PostResponse putPost(PostPutBody body, int id) {
        PostResponse response = given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.PUT, "/posts/" + id)
                .then()
                .extract()
                .as(PostResponse.class);
        return response;
    }
}
