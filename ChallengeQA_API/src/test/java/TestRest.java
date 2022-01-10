import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.fromContentType;
import static org.hamcrest.Matchers.*;
import static io.restassured.path.json.JsonPath.from;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestRest {

    @BeforeTest
    public void setUp(){

        RestAssured.baseURI = "https://api.openbrewerydb.org/";
        RestAssured.basePath= "breweries/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void GetTest() {

        Response response =
                given().contentType(JSON).queryParam("query", "lagunitas")
                        .get("autocomplete/");

        JsonPath extractor = response.jsonPath();
        String id = extractor.get("id").toString();
        System.out.println("la respuest es: " + id);
        String id2 = extractor.get("id").toString();

        String sa = response.toString();
    }
    }


