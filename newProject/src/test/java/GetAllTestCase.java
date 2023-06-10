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

public class GetAllTestCase extends  BaseClass{

    @Test
    public void getAllTest() {
       System.out.println("Get Method");

        Response response = getUser();
        assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
        assertThat(response.asString(), notNullValue());
        System.out.println("User Response"+response.asString());
    }

    @Test
    public void getPostTest() {
        System.out.println("Get Post  Method");
        Faker faker=new Faker();
        String name= faker.name().name();
        String email =  faker.internet().emailAddress();

        String getBody= "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        Response response = postUser(getBody);
        assertThat(response.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.asString(),is(notNullValue()));
       // System.out.println(" User Get Response=>"+response.asString());
        JSONObject jsonObjectUser=new JSONObject(response.asString());
        System.out.println("Get Post  response"+response.asString());
        int id =jsonObjectUser.getInt("id");
        Response res=GetDeleteUser(id);
        System.out.println("Get Delete Response=>"+res.asString());
    }
    @Test
    public void  getPutTest(){
        System.out.println("Get Put Method");
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
        JSONObject jsonObjectPut=new JSONObject(response.asString());
      //  System.out.println("Get Post Response"+response.asString());
        int id =jsonObjectPut.getInt("id");
        String updateName= faker.name().name();
        String updateEmail =  faker.internet().emailAddress();
        String updateBody= "{ \"name\": \""+updateName+"\",\n" + "  \"email\": \""+updateEmail+"\",\n" + "    \"gender\": \"male\",\n" + "    \"status\": \"inactive\"\n" + "}";
        Response updateResponse=GetUpdateUser(updateBody,id);
        assertThat(updateResponse.getStatusCode(),is(HttpStatus.SC_OK));
        System.out.println("Get Put Response="+updateResponse.asString());
        Response res=GetDeleteUser(id);
        System.out.println("Get Delete Response=>"+res.asString());
    }

    @Test
    public void  GetDeleteTest(){
        System.out.println(" Get Delete Method");
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
     //   System.out.println("Get Post Response=>"+response.asString());
        int id =jsonObject.getInt("id");
        Response deleteResponse=GetDeleteUser(id);
        assertThat(deleteResponse.getStatusCode(),is(HttpStatus.SC_NO_CONTENT));
        System.out.println("Get Delete Response =>"+deleteResponse.asString());
    }

    //Get Method
    public Response getUser() {
        Response response = given()
                .request(Method.GET, "/users");
        return response;
    }


    //Update Method
    public Response GetUpdateUser(String body,int id){

        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.PUT,"/users/"+id);
        return response;
    }

    //Delete Method
    public Response GetDeleteUser(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/users/"+id);
        return  response;
    }
}