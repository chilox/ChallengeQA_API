import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static io.restassured.path.json.JsonPath.from;
public class RestTest {

    @Before
    public void setUp(){

        RestAssured.baseURI = "https://api.openbrewerydb.org/";
        RestAssured.basePath= "breweries/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void getListTest() {


                given()
                .contentType(JSON)
                .queryParam("query", "lagunitas")
                .get("autocomplete/")
                .then().log().ifError()
                .statusCode(200)
                .body("id[0]", containsString("Lagunitas Brewing Co"));



    }
    @Test
    public void getLitstTest2(){

        String response = given().queryParam("query", "lagunitas")
                .when().get("autocomplete/")
                .then().extract().body().asString();


        ArrayList<String> lista = from(response).get("name");
        if(lista.contains("Lagunitas brewing co")){
            lista.remove(2);
        }
        for (String cadena : lista){
            response = lista.toString();
        }
        System.out.println(response.toString());




        String response2 = given().queryParam("query", "lagunitas")
        .when().get(response).then().extract().body().asString();


    }

}
