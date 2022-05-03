package client;

import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {
    private final String JSON = "application/json";

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    protected Response doGetRequest(String url) {
        return given().config(config)
                .header("Content-Type", JSON)
                .get(url);
    }

    protected Response doGetRequest(String url, String token) {
        return given().config(config)
                .header("Content-Type", JSON)
                .header("Authorization", token)
                .get(url);
    }

    protected Response doPostRequest(String url, Object body) {
        return given().config(config)
                .header("Content-Type", JSON)
                .body(body)
                .post(url);
    }

    protected Response doDeleteRequest(String url) {
        return given().config(config)
                .header("Content-Type", JSON)
                .delete(url);
    }
}
