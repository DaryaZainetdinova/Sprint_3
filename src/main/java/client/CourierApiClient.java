package client;

import io.restassured.response.Response;
import models.Courier;

public class CourierApiClient extends BaseHttpClient {
    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public Response createCourier(Courier courier) {
        return doPostRequest(baseUrl + "/api/v1/courier", courier);
    }

    public int getCourierId(Courier courier)  {
        Courier createdCourier = doPostRequest(baseUrl + "/api/v1/courier/login", courier).body().as(Courier.class);
        return createdCourier.id;
    }

    public Response authorizationCourier(Courier courier)  {
        return doPostRequest(baseUrl + "/api/v1/courier/login", courier);
    }

    public Response deleteCourier(int id){
        return doDeleteRequest(baseUrl + "/api/v1/courier/" + id);
    }
}

