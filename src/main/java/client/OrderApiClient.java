package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Order;
import models.OrderList;
import models.OrderTrack;

public class OrderApiClient extends BaseHttpClient {
    private final String baseUrl = "https://qa-scooter.praktikum-services.ru";

    @Step("create order")
    public Response createOrder(Order order) {
        return doPostRequest(baseUrl + "/api/v1/orders", order);
    }

    @Step("create order and return track id")
    public String createOrderReturnTrackId(Order order) {
        return doPostRequest(baseUrl + "/api/v1/orders", order).body().as(OrderTrack.class).track;
    }

    @Step("get orders")
    public OrderList getOrders() {
        return doGetRequest(baseUrl + "/api/v1/orders").body().as(OrderList.class);
    }

    @Step("accept order")
    public Response acceptOrder(String orderId, String courierId){
        String[][] params = {{"courierId", courierId}};
        return doPutRequest(baseUrl + "/api/v1/orders/accept/" + orderId, params);
    }

    @Step("get order by track id")
    public Response getOrderByTrackId(String track) {
        return doGetRequest(baseUrl + "/api/v1/orders/track", track);
    }
}
