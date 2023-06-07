import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import org.apache.http.HttpStatus;
public class TestCases extends BaseClass{
    @Test
    public void getAllTest() {
        System.out.println("Get Method");
        Response response = getUser();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
    }

    @Test
    public void postTest() {
        System.out.println("Post Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();

        String body= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(body);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.asString(),is(notNullValue()));
       // System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");

        Response res=deleteUser(id);
        System.out.println("response=>"+res.asString());
    }
    @Test
    public void  updateTest(){
        System.out.println("update Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();
        String body= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(body);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");

        String updateName= faker.name().name();
        String updateEmail =  faker.internet().emailAddress();
        String updateBody= "{ \"name\": \""+updateName+"\",\n" + "  \"email\": \""+updateEmail+"\",\n" + "    \"gender\": \"male\",\n" + "    \"status\": \"inactive\"\n" + "}";
        System.out.println(id);
        Response updateResponse=updateUser(updateBody,id);
        assertThat(updateResponse.getStatusCode(),is(HttpStatus.SC_OK));
        System.out.println(updateResponse.getStatusCode());
        System.out.println("updateResponse="+updateResponse.asString());
        Response res=deleteUser(id);
        System.out.println("response=>"+res.asString());
    }

    @Test
    public void  deleteTest(){
        System.out.println("delete Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();
        String body= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(body);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");

        System.out.println(id);
        Response deleteResponse=deleteUser(id);
       assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println(deleteResponse.getStatusCode());
    }

    //Get Method
    public Response getUser() {
        Response response = given()
                .request(Method.GET, "/users");
        return response;
    }


    //Update Method
    public Response updateUser(String body,int id){

        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT,"/users/"+id);
        return response;
    }

    //Delete Method
    public Response deleteUser(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/users/"+id);
        return  response;
    }

    @Test
    public void getAllTestPost() {
        System.out.println("Get Method");
        Response response = getUserPost();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
    }

    //Get Method Post
    public Response getUserPost() {
        Response response = given()
                .request(Method.GET, "/posts");
        return response;
    }

    @Test
    public void postPostTest() {
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
     //  System.out.println(" user response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
      //  System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
     //   System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        int postId=jsonObject1.getInt("id");
        System.out.println("post response"+responsePost.asString());
        Response res=deletePost(postId);
        System.out.println("response=>"+res.asString());


    }


    @Test
    public void  updateTestPost(){

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
        System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
       // System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
      //  System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        System.out.println("post response"+responsePost.asString());
        int postId=jsonObject1.getInt("id");

        String updateBody="{\n" +
                "    \"user_id\": "+id+",\n" +
                "    \"title\": \"Et tam  certe denique tristis.\",\n" +
                "    \"body\": \"Tenus vigor ut. Triduana praesentium qui. Ab repellendus tertius. Copiose adultus sit. Molestiae cubo voluptatum. Agnosco color creta. Circumvenio debilito thermae. Vinitor vesica animi. Accusantium aeneus velociter. Despirmatio comminor speciosus. Temeritas quo tamen. Alioqui explicabo dolorem. Maiores versus sono. Tantum texo acceptus. Omnis ademptio catena. Valde argumentum qui.\"\n" +
                "}";
        System.out.println(id);
        Response updateResponse=updateUserPost(updateBody,postId);
        assertThat(updateResponse.getStatusCode(),is(HttpStatus.SC_OK));
        System.out.println(updateResponse.getStatusCode());
        System.out.println("updateResponse="+updateResponse.asString());

        Response res=deletePost(postId);
        System.out.println("response=>"+res.asString());

    }
    //Update Method
    public Response updateUserPost(String updateBody,int postId){

        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .request(Method.PUT,"/posts/"+postId);
        return response;
    }

    @Test
    public void  deletePosts(){
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
        System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
      //  System.out.println("post response"+response.asString());
        int id =jsonObject.getInt("id");
        String postPostBody= "{\n" +
                "    \"title\": \"Post methods done\",\n" +
                "    \"body\": \"posts method done in user form\"\n" +
                "}";
        Response responsePost = postUserTest(postPostBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
     //   System.out.println("response=>"+responsePost.asString());
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        System.out.println("post response"+responsePost.asString());
        int postId=jsonObject1.getInt("id");

        System.out.println(id);
        Response deleteResponse=deletePost(postId);
        assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println(deleteResponse.getStatusCode());
    }

    //Delete Method Post
    public Response deletePost(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/posts/"+id);
        return  response;
    }

    @Test
    public void getTestComment() {
        System.out.println("Get Method");
        Response response = getComment();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
    }

    //Get Method Comment
    public Response getComment() {
        Response response = given()
                .request(Method.GET, "/comments");
        return response;
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
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        int postId=jsonObject1.getInt("id");
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
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        int postId=jsonObject1.getInt("id");
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
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        int postId=jsonObject1.getInt("id");
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

        Response deleteResponse=deleteComment(postId);
        assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println(deleteResponse.getStatusCode());
    }

    //Delete Method Comment
    public Response deleteComment(int postId){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/posts/"+postId);
        return  response;
    }



    @Test
    public void getTestTODo() {
        System.out.println("Get Method");
        Response response = getToDo();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
    }

    //Get Method TODos
    public Response getToDo() {
        Response response = given()
                .request(Method.GET, "/todos");
        return response;
    }


    @Test
    public void postToDoTest() {
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
        //  System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("user response"+response.asString());
        int id =jsonObject.getInt("id");
        String postToDoBody= "{\n" +
                "        \"user_id\": "+id+",\n" +
                "        \"title\": \"Ait corporis constans vespillo omnis et.\",\n" +
                "        \"due_on\": \"2023-06-29T00:00:00.000+05:30\",\n" +
                "        \"status\": \"completed\" \n" +
                "}";
        Response responsePost = postToDoTest(postToDoBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        //  System.out.println("response=>"+responsePost.asString());
        System.out.println("post Todo response"+responsePost.asString());

    }
    //Post ToDos Method
    public Response postToDoTest( String postCommentBody,int id){
        Response response = given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(postCommentBody)
                .when()
                .request(Method.POST, "/users/"+id+"/todos");
        return response;
    }


    @Test
    public void  updateTestToDo(){

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
        //  System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("user response"+response.asString());
        int id =jsonObject.getInt("id");
        String postToDoBody= "{\n" +
                "        \"user_id\": "+id+",\n" +
                "        \"title\": \"Ait corporis constans vespillo omnis et.\",\n" +
                "        \"due_on\": \"2023-06-29T00:00:00.000+05:30\",\n" +
                "        \"status\": \"completed\" \n" +
                "}";
        Response responsePost = postToDoTest(postToDoBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        //  System.out.println("response=>"+responsePost.asString());
        System.out.println("post Todo response"+responsePost.asString());
        JSONObject jsonObject1=new JSONObject(responsePost.asString());
        // System.out.println("post response"+responsePost.asString());
        int postId=jsonObject1.getInt("id");

        String updateBody="{\n" +
                "        \"user_id\": "+id+",\n" +
                "        \"title\": \"Ait  constans vespillo omnis et.\",\n" +
                "        \"due_on\": \"2023-08-29T00:00:00.000+05:30\",\n" +
                "        \"status\": \"completed\" \n" +
                "}";

        Response updateResponse=updateUserTodo(updateBody,postId);
        assertThat(updateResponse.getStatusCode(),is(HttpStatus.SC_OK));
        //  System.out.println(updateResponse.getStatusCode());
        System.out.println("updateTODOResponse="+updateResponse.asString());

    }
    //Update Method
    public Response updateUserTodo(String updateBody,int postId){

        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .request(Method.PUT,"/todos/"+postId);
        return response;
    }


    @Test
    public void  deleteToDo(){
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
        //  System.out.println("response=>"+response.asString());
        JSONObject jsonObject=new JSONObject(response.asString());
        System.out.println("user response"+response.asString());
        int id =jsonObject.getInt("id");
        String postToDoBody= "{\n" +
                "        \"user_id\": "+id+",\n" +
                "        \"title\": \"Ait corporis constans vespillo omnis et.\",\n" +
                "        \"due_on\": \"2023-06-29T00:00:00.000+05:30\",\n" +
                "        \"status\": \"completed\" \n" +
                "}";
        Response responsePost = postToDoTest(postToDoBody,id);
        assertThat(responsePost.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePost.asString(),is(notNullValue()));
        //  System.out.println("response=>"+responsePost.asString());
        System.out.println("post Todo response"+responsePost.asString());
        JSONObject jsonObjectToDo=new JSONObject(responsePost.asString());
        int postId=jsonObjectToDo.getInt("id");
        System.out.println("post response"+responsePost.asString());

        Response deleteResponse=deleteToDo(postId);
        assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println(deleteResponse.getStatusCode());
    }

    //Delete Method ToDos
    public Response deleteToDo(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/todos/"+id);
        return  response;
    }




}



