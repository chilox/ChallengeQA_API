import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static io.restassured.path.json.JsonPath.from;
public class RestTest {

    @Before
    public void setUp(){
        //Metod el cual seta la URI
        RestAssured.baseURI = "https://api.openbrewerydb.org/";
        RestAssured.basePath= "breweries/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void getLitstTest2(){
        //Se obtiene del listado de cervecerias la cuales contienen la palabra Lagunitas
        String response = given().queryParam("query", "lagunitas")
                .when().get("autocomplete/")
                .then().extract().body().asString();

        //Se genera un arreglo donde se almacenan las cervecerias
        ArrayList<String> lista = from(response).get("id");
        if(lista.contains("Lagunitas Brewing Co")){
            lista.remove(2);
        }
        for (String cadena : lista){
            response = lista.toString();
        }
        System.out.println(lista.toString());

        String a = lista.get(0).toString();
        String b = lista.get(1).toString();
        System.out.println(a.toString());
        System.out.println(b.toString());


        //Por medio de este GET se realiza el filtrado de la cerveceria con sus respectivos assert
        String response2 = given().queryParam("query", "lagunitas")
        .when().get(b)
        .then().assertThat().body("state", Is.is("California"))
                .assertThat().body("street", Is.is("1280 N McDowell Blvd"))
                .assertThat().body("phone", Is.is("7077694495"))
                .extract().body().asString();

        System.out.println(response2);




    }

}
