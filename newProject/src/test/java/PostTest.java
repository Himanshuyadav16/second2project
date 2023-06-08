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

public class PostTest extends BaseClass{
@Test
public void getPostTest() {
    System.out.println("Get Post Test");
    Response responsePost=getPost();
    assertThat(responsePost.getStatusCode(),is(HttpStatus.SC_OK));
    assertThat(responsePost.asString(),is(notNullValue()));
    System.out.println("Get Response Post=>"+responsePost.asString());
}

@Test
public void postPostsTest(){
    System.out.println("Post Posts Method");
    Faker faker=new Faker();
    String userName=faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userBody = "{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responseUser = postUser(userBody);
    assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responseUser.asString(), is(notNullValue()));

    JSONObject jsonObjectUser=new JSONObject(responseUser.asString());
    int idUser=jsonObjectUser.getInt("id");

    String postBody=" { \n" +
            "    \"title\": \"Et tam  curso certe denique tristis.\",\n" +
            "    \"body\":\"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
            "}";
    Response responsePost=postPosts(postBody,idUser);
    assertThat(responsePost.getStatusCode(),is(HttpStatus.SC_CREATED));
    assertThat(responsePost.asString(),is(notNullValue()));
    System.out.println("Post Response Post=>"+responsePost.asString());
}

@Test
public void putPostTest(){
    System.out.println("Put Posts Method");

    Faker faker=new Faker();
    String userName=faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userBody = "{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responseUser = postUser(userBody);
    assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responseUser.asString(), is(notNullValue()));

    JSONObject jsonObjectUser=new JSONObject(responseUser.asString());
    int idUser=jsonObjectUser.getInt("id");

    String postBody=" { \n" +
            "    \"title\": \"Et tam  curso certe denique tristis.\",\n" +
            "    \"body\":\"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
            "}";
    Response responsePost=postPosts(postBody,idUser);
    assertThat(responsePost.getStatusCode(),is(HttpStatus.SC_CREATED));
    assertThat(responsePost.asString(),is(notNullValue()));

    JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
    int idPost=jsonObjectPost.getInt("id");
    String putBody="{\n" +
            "    \"user_id\": "+idUser+",\n" +
            "    \"title\": \"Et tam  certe denique istis.\",\n" +
            "    \"body\": \"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
            "}";
    Response responsePut=putPosts(putBody,idPost);
    assertThat(responsePut.getStatusCode(),is(HttpStatus.SC_OK));
    assertThat(responsePut.asString(),is(notNullValue()));
    System.out.println("Put Response Post "+responsePut.asString());

}
@Test
public void deletePostTest(){
    System.out.println("Put Posts Method");

    Faker faker=new Faker();
    String userName=faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userBody = "{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responseUser = postUser(userBody);
    assertThat(responseUser.getStatusCode(), is(HttpStatus.SC_CREATED));
    assertThat(responseUser.asString(), is(notNullValue()));

    JSONObject jsonObjectUser=new JSONObject(responseUser.asString());
    int idUser=jsonObjectUser.getInt("id");

    String postBody=" { \n" +
            "    \"title\": \"Et tam  curso certe denique tristis.\",\n" +
            "    \"body\":\"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
            "}";
    Response responsePost=postPosts(postBody,idUser);
    assertThat(responsePost.getStatusCode(),is(HttpStatus.SC_CREATED));
    assertThat(responsePost.asString(),is(notNullValue()));

    JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
    int idPost=jsonObjectPost.getInt("id");

    Response responseDeletePost=deletePosts(idPost);
    System.out.println("Response Delete Post=>"+responseDeletePost.asString());
}

// Post Get Method
    public Response getPost(){
    Response response=given()
            .request(Method.GET,"/posts");
    return response;
    }

    //Post Posts Test
    public Response postPosts(String body,int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST,"/users/"+id+"/posts");
        return response;
    }


    // Put Post Method
    public Response putPosts(String body,int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT,"/posts/"+id);

        return response;
    }

    //Post Delete Method
    public Response deletePosts(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/posts/"+id);
        return response;
    }


}
