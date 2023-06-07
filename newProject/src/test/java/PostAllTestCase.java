import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PostAllTestCase extends BaseClass {


    @Test
    public void postGetTest() {
        System.out.println("Post Get Method");
        Response response = postUser();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
    }



    @Test
    public void postPostTest() {
        System.out.println("Post Post Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();

        String postUserBody= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(postUserBody);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.asString(),is(notNullValue()));
       // System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        //System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        //System.out.println(" Response =>"+responsePost.asString());
        JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
        int postId=jsonObjectPost.getInt("id");
        System.out.println("Post Response"+responsePost.asString());
        Response res=deletePost(postId);
        System.out.println("Delete Post Response=>"+res.asString());

    }

    @Test
    public void  PostPutTest(){

        System.out.println("Post Put Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();

        String postUserBody= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(postUserBody);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.asString(),is(notNullValue()));
      //  System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        //System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
       // System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
       // System.out.println("post response"+responsePost.asString());
        int postId=jsonObjectPost.getInt("id");

        String updateBody="{\n" +
                "    \"user_id\": "+id+",\n" +
                "    \"title\": \"Et tam  certe denique tristis.\",\n" +
                "    \"body\": \"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
                "}";

        Response updateResponse=updateUserPost(updateBody,postId);
        assertThat(updateResponse.getStatusCode(),is(HttpStatus.SC_OK));
       // System.out.println(updateResponse.getStatusCode());
        System.out.println("Post Put Response="+updateResponse.asString());

        Response res=deletePost(postId);
        System.out.println("Post Delete Response=>"+res.asString());

    }


    @Test
    public void  postDelete(){
        System.out.println("Post Delete  Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();

        String postUserBody= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(postUserBody);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.asString(),is(notNullValue()));
        System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
        System.out.println("post response"+responsePost.asString());
        int postId=jsonObjectPost.getInt("id");


       Response deleteResponse=deletePost(postId);
        assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println("Post Delete Response =>"+deleteResponse.asString());
    }

    //Get Method Post
    public Response postUser() {
        Response response = given()
                .request(Method.GET, "/posts");
        return response;
    }

    //Update Method Post
    public Response updateUserPost(String updateBody,int postId){

        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .request(Method.PUT,"/posts/"+postId);
        return response;
    }


    //Delete Method Post
    public Response deletePost(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/posts/"+id);
        return  response;
    }

}