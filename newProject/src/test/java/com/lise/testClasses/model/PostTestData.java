//package com.lise.testClasses.model;
//
//import com.github.javafaker.Faker;
//import com.lise.BaseClass;
//import com.lise.modals.PostResponse;
//import com.lise.modals.UserResponse;
//import io.restassured.http.ContentType;
//import io.restassured.http.Method;
//
//import io.restassured.response.Response;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//public class PostTestData extends BaseClass {
//
//    @Test
//    public void getPostTestData() {
//        PostResponse[] postResponse = getPosts();
//
//        assertThat(postResponse[0].getId(), is(notNullValue()));
//        assertThat(postResponse[0].getUser_id(), is(notNullValue()));
//        assertThat(postResponse[0].getTitle(), is(notNullValue()));
//        assertThat(postResponse[0].getBody(), is(notNullValue()));
//    }
//
//    @Test
//    public void postPostsTestData() {
//        Faker faker = new Faker();
//        String userName = faker.name().name();
//        String userEmail = faker.internet().emailAddress();
//        String userGender = "male";
//        String userStatus = "active";
//        String postsTitle = "Et tam  curso certe denique tristis.";
//        String postsBody = "Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.";
//
//        String userBody = "{\n " +
//                "\"name\":\"" + userName + "\",\n" +
//                "\"email\":\"" + userEmail + "\",\n" +
//                "\"gender\":\"" + userGender + "\",\n" +
//                "\"status\":\"" + userStatus + "\"\n" +
//                "}";
//
//        String postBody = " { \n" +
//                "    \"title\": \"" + postsTitle + "\",\n" +
//                "    \"body\":\"" + postsBody + "\"\n" +
//                "}";
//
//        UserResponse postUserResponse = postUser(userBody);
//
//        int userId = postUserResponse.getId();
//
//        assertThat(postUserResponse.getId(), is(notNullValue()));
//        assertThat(postUserResponse.getName(), is(userName));
//        assertThat(postUserResponse.getEmail(), is(userEmail));
//        assertThat(postUserResponse.getGender(), is(userGender));
//        assertThat(postUserResponse.getStatus(), is(userStatus));
//
//        PostResponse postResponse = postPosts(postBody, userId);
//
//        int postId = postResponse.getId();
//
//        assertThat(postResponse.getId(), is(postId));
//        assertThat(postResponse.getUser_id(), is(userId));
//        assertThat(postResponse.getTitle(), is(postsTitle));
//        assertThat(postResponse.getBody(), is(postsBody));
//
//        Response deletePostResponse = deletePosts(postId);
//
//        Response deleteUserResponse = deleteUsers(userId);
//    }
//
//    @Test
//    public void putPostTestData() {
//        Faker faker = new Faker();
//        String userName = faker.name().name();
//        String userEmail = faker.internet().emailAddress();
//        String userGender = "male";
//        String userStatus = "active";
//        String postsTitle = "Et tam  certe denique istis.";
//        String postsBody = "Tenus vigor ut. Triduana praesentiu qui. Ab repellendus tertius. Copiose adultus sit.";
//        String putPostTitle = "Et tam  certe denique istis.";
//        String putPostBody = "Tenus vigor ut. Triduana praesentiu qui. Ab repellendus tertius. Copiose adultus sit.";
//        String userBody = "{\n " +
//                "\"name\":\"" + userName + "\",\n" +
//                "\"email\":\"" + userEmail + "\",\n" +
//                "\"gender\":\"" + userGender + "\",\n" +
//                "\"status\":\"" + userStatus + "\"\n" +
//                "}";
//
//        String postBody = " { \n" +
//                "    \"title\": \"" + postsTitle + "\",\n" +
//                "    \"body\":\"" + postsBody + "\"\n" +
//                "}";
//
//        UserResponse postUserResponse = postUser(userBody);
//
//        int userId = postUserResponse.getId();
//
//        assertThat(postUserResponse.getId(), is(notNullValue()));
//        assertThat(postUserResponse.getName(), is(userName));
//        assertThat(postUserResponse.getEmail(), is(userEmail));
//        assertThat(postUserResponse.getGender(), is(userGender));
//        assertThat(postUserResponse.getStatus(), is(userStatus));
//
//        PostResponse postResponse = postPosts(postBody, userId);
//
//        int postId = postResponse.getId();
//
//        assertThat(postResponse.getId(), is(postId));
//        assertThat(postResponse.getUser_id(), is(userId));
//        assertThat(postResponse.getTitle(), is(postsTitle));
//        assertThat(postResponse.getBody(), is(postsBody));
//
//        String putBody = "{\n" +
//                "    \"user_id\": " + userId + ",\n" +
//                "    \"title\": \"" + putPostTitle + "\",\n" +
//                "    \"body\": \"" + putPostBody + "\"\n" +
//                "}";
//        PostResponse putResponse = putPost(putBody, postId);
//
//        int putId = putResponse.getId();
//
//        assertThat(putResponse.getTitle(), is(putPostTitle));
//        assertThat(putResponse.getBody(), is(putPostBody));
//
//        Response deletePutResponse = deletePosts(putId);
//
//        Response deletePostResponse = deletePosts(postId);
//
//        Response deleteUserResponse = deleteUsers(userId);
//    }
//
//
//    // Post Get Method
//    public PostResponse[] getPosts() {
//        PostResponse[] response = given()
//                .request(Method.GET, "/posts")
//                .then()
//                .extract()
//                .as(PostResponse[].class);
//        return response;
//    }
//
//
//    // Put Post Method
//    public PostResponse putPost(String body, int id) {
//        PostResponse response = given()
//                .header("Authorization", accessToken)
//                .contentType(ContentType.JSON)
//                .body(body)
//                .request(Method.PUT, "/posts/" + id)
//                .then()
//                .extract()
//                .as(PostResponse.class);
//        return response;
//    }
//}
