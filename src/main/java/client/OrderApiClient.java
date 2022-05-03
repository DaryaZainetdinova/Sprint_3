package client;

import io.restassured.response.Response;
import models.Order;
import models.OrderList;

public class OrderApiClient extends BaseHttpClient {
    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public Response createOrder(Order order) {
        return doPostRequest(baseUrl + "/api/v1/orders", order);
    }

    public OrderList getOrders() {
        return doGetRequest(baseUrl + "/api/v1/orders").body().as(OrderList.class);
    }
}

