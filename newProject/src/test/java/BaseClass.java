import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BaseClass {
    String accessToken;

    @BeforeSuite
       void beforeGetUser()
        {
            RestAssured.baseURI = ApplicationProperties.INSTANCE.getUrl();
             accessToken=ApplicationProperties.INSTANCE.getToken();
            System.out.println("Before Suite");
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


    //Post Posts Method
    public Response postPosts( String postPostBody,int id){
        Response response = given()
                .header("Authorization","Bearer a6fc195dd60e618c4f0d37e15ae429917d090fe68d9ca16fd847681cddc448fa")
                .contentType(ContentType.JSON)
                .body(postPostBody)
                .when()
                .request(Method.POST, "/users/"+id+"/posts");
        return response;
    }
}