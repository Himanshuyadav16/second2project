import com.github.javafaker.Faker;
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

public class PostTest extends BaseClass{
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

    Response postUserResponse = postUser(userBody);

    assertThat(postUserResponse.getStatusCode(), is(HttpStatus.SC_CREATED));

    JSONObject jsonObjectUser= new JSONObject(postUserResponse.asString());

    int idUser=jsonObjectUser.getInt("id");
    assertThat(jsonObjectUser.getInt("id"),is(notNullValue()));
    assertThat(jsonObjectUser.getString("name"),equalTo(userName));
    assertThat(jsonObjectUser.getString("email"),equalTo(userEmail));
    assertThat(jsonObjectUser.getString("gender"),equalTo(userGender));
    assertThat(jsonObjectUser.getString("status"),equalTo(userStatus));


    String postsTitle="Et tam  curso certe denique tristis.";
    String postsBody="Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.";

    String postBody=" { \n" +
            "    \"title\": \""+postsTitle+"\",\n" +
            "    \"body\":\""+postsBody+"\"\n" +
            "}";

    Response postResponse=postPosts(postBody,idUser);

    assertThat(postResponse.getStatusCode(),is(HttpStatus.SC_CREATED));

    JSONObject jsonObjectPost=new JSONObject(postResponse.asString());

    int idPost=jsonObjectPost.getInt("id");

    assertThat(jsonObjectPost.getInt("id"),equalTo(idPost));
    assertThat(jsonObjectPost.getInt("user_id"),equalTo(idUser));
    assertThat(jsonObjectPost.getString("title"),equalTo(postsTitle));
    assertThat(jsonObjectPost.getString("body"),equalTo(postsBody));
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
    Response PutResponse=putPosts(putBody,idPost);
    assertThat(PutResponse.getStatusCode(),is(HttpStatus.SC_OK));
    assertThat(PutResponse.asString(),is(notNullValue()));
    System.out.println("Put Response Post "+PutResponse.asString());

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

    Response DeletePostResponse=deletePosts(idPost);
    System.out.println("Response Delete Post=>"+DeletePostResponse.asString());
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
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST,"/users/"+id+"/posts");
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
