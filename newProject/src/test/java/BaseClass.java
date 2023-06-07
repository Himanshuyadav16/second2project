import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BaseClass {
    @Test
    public void getApplicationProperty() {
        System.out.println(ApplicationProperties.INSTANCE.getUrl());
        System.out.println(ApplicationProperties.INSTANCE.getToken());
    }
       @BeforeSuite
       void beforeGetUser()
        {
            RestAssured.baseURI = ApplicationProperties.INSTANCE.getUrl();
            System.out.println("before suite");
        }
    //Post Method
    public Response postUser(String body){
        Response response = given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .request(Method.POST, "/users");
        return response;
    }
    //Post Posts Method
    public Response postUserTest( String postPostBody,int id){
        Response response = given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(postPostBody)
                .when()
                .request(Method.POST, "/users/"+id+"/posts");
        return response;
    }






}