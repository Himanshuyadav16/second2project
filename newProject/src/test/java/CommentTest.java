import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.Method;

public class CommentTest extends BaseClass {
    @Test
    public void getCommentTest() {
        System.out.println("Get Comment Test");
        Response responseComment = getComment();
        assertThat(responseComment.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(responseComment.asString(), is(notNullValue()));
        System.out.println(responseComment.asString());
    }

    @Test
    public void postCommentTest() {
        System.out.println("Post Comment Test");
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"male\",\n" +
                "\"status\":\"active\"\n" +
                "}";
        Response responseUser = postUser(userBody);
        assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseUser.asString(), is(notNullValue()));

        JSONObject jsonObjectUser = new JSONObject(responseUser.asString());
        int idUser = jsonObjectUser.getInt("id");

        String postBody = " { \n" +
                "    \"title\": \"Et tam  curso certe denique tristis.\",\n" +
                "    \"body\":\"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
                "}";
        Response responsePost = postPosts(postBody, idUser);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(), is(notNullValue()));

        JSONObject jsonObjectPost = new JSONObject(responsePost.asString());
        int postId = jsonObjectPost.getInt("id");

        String postCommentBody = " {\n" +
                "        \"post_id\": " + postId + ",\n" +
                "        \"name\": \"Sharmila Gaaka\",\n" +
                "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
                "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
                "    }";
        Response responsePostComment = postComment(postCommentBody, postId);
        assertThat(responsePostComment.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePostComment.asString(), is(notNullValue()));
        System.out.println(" Post Response  Comment=>" + responsePostComment.asString());
    }

    @Test
    public void putCommentTest() {
        System.out.println("Put Comment Test");
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"male\",\n" +
                "\"status\":\"active\"\n" +
                "}";
        Response responseUser = postUser(userBody);
        assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseUser.asString(), is(notNullValue()));

        JSONObject jsonObjectUser = new JSONObject(responseUser.asString());
        int idUser = jsonObjectUser.getInt("id");

        String postBody = " { \n" +
                "    \"title\": \"Et tam  curso certe denique tristis.\",\n" +
                "    \"body\":\"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
                "}";
        Response responsePost = postPosts(postBody, idUser);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(), is(notNullValue()));

        JSONObject jsonObjectPost = new JSONObject(responsePost.asString());
        int postId = jsonObjectPost.getInt("id");

        String postCommentBody = " {\n" +
                "        \"post_id\": " + postId + ",\n" +
                "        \"name\": \"Sharmila Gaaka\",\n" +
                "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
                "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
                "    }";
        Response responsePostComment = postComment(postCommentBody, postId);
        assertThat(responsePostComment.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePostComment.asString(), is(notNullValue()));

        JSONObject jsonObjectComment = new JSONObject(responsePostComment.asString());
        int postIdComment = jsonObjectComment.getInt("id");
        String putCommentBody = " {\n" +
                "        \"post_id\": " + postId + ",\n" +
                "        \"name\": \"Sharmila Ganaka\",\n" +
                "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
                "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
                "    }";
        Response responsePutComment = putComment(putCommentBody, postIdComment);
        assertThat(responsePutComment.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(responsePutComment.asString(), is(notNullValue()));
        System.out.println("Put Response Comment=>" + responsePutComment.asString());
    }
@Test
public void  deleteCommentTest(){
    System.out.println("Delete Comment Test");
    Faker faker = new Faker();
    String userName = faker.name().name();
    String userEmail = faker.internet().emailAddress();
    String userBody = "{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responseUser = postUser(userBody);
    assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responseUser.asString(), is(notNullValue()));

    JSONObject jsonObjectUser = new JSONObject(responseUser.asString());
    int idUser = jsonObjectUser.getInt("id");

    String postBody = " { \n" +
            "    \"title\": \"Et tam  curso certe denique tristis.\",\n" +
            "    \"body\":\"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
            "}";
    Response responsePost = postPosts(postBody, idUser);
    assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responsePost.asString(), is(notNullValue()));

    JSONObject jsonObjectPost = new JSONObject(responsePost.asString());
    int postId = jsonObjectPost.getInt("id");

    String postCommentBody = " {\n" +
            "        \"post_id\": " + postId + ",\n" +
            "        \"name\": \"Sharmila Gaaka\",\n" +
            "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
            "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
            "    }";
    Response responsePostComment = postComment(postCommentBody, postId);
    assertThat(responsePostComment.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responsePostComment.asString(), is(notNullValue()));

    JSONObject jsonObjectComment = new JSONObject(responsePostComment.asString());
    int postIdComment = jsonObjectComment.getInt("id");
     Response responseDeleteComment=deleteComment(postIdComment);
     assertThat(responseDeleteComment.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
    System.out.println("Delete Response Comment=>"+responseDeleteComment.asString());



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
                .header("Authorization", "Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/posts/" + postId + "/comments");

        return response;
    }

    // Put Comment Method
    public Response putComment(String body, int postIdComment) {
        Response response = given()
                .header("Authorization", "Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT, "/comments/" + postIdComment);
        return response;
    }


    //Delete Comment Method
    public Response deleteComment(int id) {
        Response response = given()
                .header("Authorization", "Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE, "/comments/" + id);
        return response;
    }
}