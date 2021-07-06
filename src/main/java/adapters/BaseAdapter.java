package adapters;

import com.google.gson.Gson;
import io.restassured.response.Response;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAdapter {
    public static final String BASE_URL = "https://api.qase.io/v1/";
    public static final String CONTENT_TYPE = "application/json";
    String token = System.getenv().getOrDefault("token", PropertyReader.getProperty("token"));
    Gson converter = new Gson();

    public String get(String url) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", CONTENT_TYPE)
                .when()
                        .get(BASE_URL + url)
                .then()
                        .log().all()
                        .extract().body().asString();
    }

    public String post(String url, String body) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", CONTENT_TYPE)
                        .body(body)
                .when()
                        .post(BASE_URL + url)
                .then()
                        .log().all()
                        .extract().body().asString();
    }

    public Response delete(String url) {
        return
                given()
                        .header("Token", token)
                        .header("Content-Type", CONTENT_TYPE)
                .when()
                        .delete(BASE_URL + url)
                .then()
                        .log().all()
                        .extract().response();
    }
}
