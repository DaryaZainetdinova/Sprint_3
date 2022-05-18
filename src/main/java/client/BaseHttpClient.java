package client;

import io.qameta.allure.Step;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {
    private final String JSON = "application/json";

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    @Step("Send get request to \"{url}\"")
    protected Response doGetRequest(String url) {
        return given().config(config)
                .header("Content-Type", JSON)
                .get(url);
    }

    @Step("Send get request to \"{url}\"")
    protected Response doGetRequest(String url, String trackId) {
        return given().config(config)
                .header("Content-Type", JSON)
                .param("t", trackId)
                .get(url);
    }

    @Step("Send post request to \"{url}\"")
    protected Response doPostRequest(String url, Object body) {
        return given().config(config)
                .header("Content-Type", JSON)
                .body(body)
                .post(url);
    }

    @Step("Send delete request to \"{url}\"")
    protected Response doDeleteRequest(String url) {
        return given().config(config)
                .header("Content-Type", JSON)
                .delete(url);
    }


    @Step("Send put request to \"{url}\"")
    protected Response doPutRequest(String url) {
        return given().config(config)
                .header("Content-Type", JSON)
                .put(url);
    }

    @Step("Send put request to \"{url}\"")
    protected Response doPutRequest(String url, String[][] params) {
        RequestSpecification request = given().config(config)
                .header("Content-Type", JSON);

        for (String[] param : params) {
            request.queryParam(param[0], param[1]);
        }

        return request.put(url);
    }
}
