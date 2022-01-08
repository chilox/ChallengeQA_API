import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestTest {

    @Before
    public void before(){

        RestAssured.baseURI = "https://api.openbrewerydb.org/breweries/";
    }

    @Test
    public void getListTest() {

        RestAssured
                .given().log().all().param("query", "lagunitas")
                .get("/autocomplete")
                .then().log().ifError()
                .statusCode(200)
                .body("id[0]", startsWith("lagunitas"))
                .and()
                .body("name[0]",containsString("Lagunitas Brewing Co"));


    }

}
