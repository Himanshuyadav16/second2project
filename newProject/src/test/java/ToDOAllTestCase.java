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

public class ToDOAllTestCase extends BaseClass {

    @Test
    public void getTestTODo() {
        System.out.println("Get Method");
        Response response = getToDo();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println(response.asString());
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
            JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
                  int postId=jsonObjectPost.getInt("id");
                  System.out.println("post response"+responsePost.asString());
                  Response res=deleteToDo(postId);
                  System.out.println("response=>"+res.asString());

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
        JSONObject jsonObjectPost=new JSONObject(responsePost.asString());
       // System.out.println("post response"+responsePost.asString());
        int postId=jsonObjectPost.getInt("id");

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


                Response res=deleteToDo(postId);
                System.out.println("response=>"+res.asString());
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



    //Get Method TODos
    public Response getToDo() {
        Response response = given()
                .request(Method.GET, "/todos");
        return response;
    }

    //Update Method Todos
    public Response updateUserTodo(String updateBody,int postId){

        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .request(Method.PUT,"/todos/"+postId);
        return response;
    }

    //Delete Method ToDos
    public Response deleteToDo(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/todos/"+id);
        return  response;
    }

}
