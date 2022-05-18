package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Courier;

public class CourierApiClient extends BaseHttpClient {
    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    @Step("send create courier request")
    public Response createCourier(Courier courier) {
        return doPostRequest(baseUrl + "/api/v1/courier", courier);
    }

    @Step("get courier id")
    public String getCourierId(Courier courier)  {
        Courier createdCourier = doPostRequest(baseUrl + "/api/v1/courier/login", courier).body().as(Courier.class);
        return createdCourier.id;
    }

    @Step("autorization courier")
    public Response authorizationCourier(Courier courier)  {
        return doPostRequest(baseUrl + "/api/v1/courier/login", courier);
    }

    @Step("delete courier")
    public Response deleteCourier(String id){
        return doDeleteRequest(baseUrl + "/api/v1/courier/" + id);
    }
}

