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

public class CommentAllTestCase extends BaseClass{

    @Test
    public void getTestComment() {
        System.out.println(" Comment Get Method");
        Response response = getComment();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
    }

    @Test
    public void postCommentTest() {
        System.out.println("Post Method");
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
     //   System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("user response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "        \"post_id\": "+id+",\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
       // System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
        int postId=jsonObjectPost.getInt("id");
        System.out.println("post response"+responsePost.asString());
        String postCommentBody= " {\n" +
                "        \"post_id\": "+postId+",\n" +
                "        \"name\": \"Sharmila Gaaka\",\n" +
                "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
                "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
                "    }";
        Response responseComment = postCommentTest(postCommentBody,postId);
        assertThat(responseComment.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseComment.asString(),is(notNullValue()));
        System.out.println("response comment=>"+responseComment.asString());

        Response res=deleteComment(postId);
        System.out.println("response=>"+res.asString());

    }

    //Post comment Method
    public Response postCommentTest( String postCommentBody,int postId){
        Response response = given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(postCommentBody)
                .when()
                .request(Method.POST, "/posts/"+postId+"/comments");
        return response;
    }

    @Test
    public void  updateTestComment(){
        System.out.println("Post Method");
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
        //   System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("user response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "        \"post_id\": "+id+",\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        // System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
        int postId=jsonObjectPost.getInt("id");
        System.out.println("post response"+responsePost.asString());


        String postCommentBody= " {\n" +
                "        \"post_id\": "+postId+",\n" +
                "        \"name\": \"Sharmila Gaaka\",\n" +
                "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
                "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
                "    }";
        Response responseComment = postCommentTest(postCommentBody,postId);

        assertThat(responseComment.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseComment.asString(),is(notNullValue()));
        System.out.println("response comment=>"+responseComment.asString());
       JSONObject jsonObjectComment =new JSONObject(responseComment.asString());
        int postIdComment=jsonObjectComment.getInt("id");


        String updateBody="{       \"post_id\": "+postId+",\n" +
                "        \"name\": \"Sharla anaka\",\n" +
                "        \"email\": \"sharmaka@ntzsche.test\",\n" +
                "        \"body\": \" accusamus assumenda. Sint vero ipsum.\"\n" +
                "}";
        Response updateResponse=updateUserComment(updateBody,postIdComment);
        assertThat(updateResponse.getStatusCode(),is(HttpStatus.SC_OK));
        System.out.println(updateResponse.getStatusCode());
        System.out.println("updateResponseComment="+updateResponse.asString());
        Response res=deleteComment(postId);
        System.out.println("response=>"+res.asString());

    }



    @Test
    public void  deleteComment(){
        System.out.println("Post Method");
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
        //   System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
       // System.out.println("user response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "        \"post_id\": "+id+",\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        // System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObjectComment=new JSONObject(responsePost.asString());
        int postId=jsonObjectComment.getInt("id");
       // System.out.println("post response"+responsePost.asString());


        String postCommentBody= " {\n" +
                "        \"post_id\": "+postId+",\n" +
                "        \"name\": \"Sharmila Gaaka\",\n" +
                "        \"email\": \"sharmila_ganaka@nitzsche.test\",\n" +
                "        \"body\": \"Provident eos voluptas. Iusto accusamus assumenda. Sint vero ipsum.\"\n" +
                "    }";
        Response responseComment = postCommentTest(postCommentBody,postId);

        assertThat(responseComment.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responseComment.asString(),is(notNullValue()));

        System.out.println("response comment=>"+responseComment.asString());
        JSONObject jsonObjectCommentDelete=new JSONObject(responseComment.asString());
        int postCommentId=jsonObjectCommentDelete.getInt("id");
        Response deleteResponse=deleteComment(postCommentId);
        assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println("Delete Response =>"+deleteResponse.asString());
    }

    //Get Method Comment
    public Response getComment() {
        Response response = given()
                .request(Method.GET, "/comments");
        return response;
    }



    //Update Comment Method
    public Response updateUserComment(String updateBody,int postIdComment){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .request(Method.PUT,"/comments/"+postIdComment);
        return response;
    }


    //Delete Method Comment
    public Response deleteComment(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/comments/" + id);
        return  response;
    }

}
