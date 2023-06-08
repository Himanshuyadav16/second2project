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


public class UserTest extends BaseClass{
    @Test
    public void getUserTest() {
        System.out.println("Get User Test");
         Response responseUser=getUser();
         assertThat(responseUser.getStatusCode(),is(HttpStatus.SC_OK));
         System.out.println( "Response User=>"+responseUser.asString());
    }
    @Test
    public void postUserTest() {
        System.out.println("Post User Test");
        Faker faker = new Faker();
        String userName = faker.name().name();
        String userEmail = faker.internet().emailAddress();
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"male\",\n" +
                "\"status\":\"active\"\n" +
                "}";
        Response responsePostUser = postUsers(userBody);
         assertThat(responsePostUser.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePostUser.asString(), is(notNullValue()));
        System.out.println("Response Post User =>"+responsePostUser.asString());
    }

    @Test
    public void putUserTest(){
        System.out.println("Put User Test");
        Faker faker = new Faker();
        String userName=faker.name().name();
        String userEmail=faker.internet().emailAddress();
        String userBody = "{\n " +
                "\"name\":\"" + userName + "\",\n" +
                "\"email\":\"" + userEmail + "\",\n" +
                "\"gender\":\"male\",\n" +
                "\"status\":\"active\"\n" +
                "}";
        Response responsePostUser = postUsers(userBody);
        assertThat(responsePostUser.getStatusCode(), is(HttpStatus.SC_CREATED));
        assertThat(responsePostUser.asString(), is(notNullValue()));

        JSONObject jsonObjectUser=new JSONObject(responsePostUser.asString());
        int idUser=jsonObjectUser.getInt("id");
        String updateUserName=faker.name().name();
        String updateUserEmail=faker.internet().emailAddress();
        String updateUserBody="  {\n" +
                "    \"email\":\""+updateUserEmail+"\",\n" +
                "    \"name\":\""+updateUserName+"\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\":\"active\"\n" +
                "}";
        Response responsePutUser=putUser(updateUserBody,idUser);
        assertThat(responsePutUser.getStatusCode(),is(HttpStatus.SC_OK));
        assertThat(responsePutUser.asString(),is(notNullValue()));
        System.out.println("Response Put User=>"+responsePutUser.asString());
    }

@Test
public void deleteUserTest(){
    System.out.println("Delete User Test");
    Faker faker= new Faker();
    String userName =faker.name().name();
    String userEmail=faker.internet().emailAddress();
    String userBody="{\n " +
            "\"name\":\"" + userName + "\",\n" +
            "\"email\":\"" + userEmail + "\",\n" +
            "\"gender\":\"male\",\n" +
            "\"status\":\"active\"\n" +
            "}";
    Response responsePostUser=postUsers(userBody);
    assertThat(responsePostUser.getStatusCode(),is(HttpStatus.SC_CREATED));
    assertThat(responsePostUser.asString(),is(notNullValue()));
    JSONObject  jsonObjectUser=new JSONObject(responsePostUser.asString());
    int idUser=jsonObjectUser.getInt("id");
    Response responseDeleteUser=deleteUsers(idUser);
    System.out.println("Response Delete User=>"+responseDeleteUser.asString());

}

    //User Get Method
    public Response getUser() {
        Response response= given()
                .request(Method.GET,"/users");
        return response;
    }

    //User Post Method
    public Response postUsers(String userBody){
        Response response= given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON )
                .body(userBody)
                .when()
                .request(Method.POST,"/users");
        return  response;
    }

    // User Put Method
    public Response putUser(String updateUserBody,int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .when()
                .request(Method.PUT,"/users/"+id);
        return  response;

    }

    //  User Delete Method
    public Response deleteUsers(int id){
        Response response=given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .request(Method.DELETE,"/users/"+id);
        return response;
    }



}