import client.OrderApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Order;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetOrderByTrackTests {
    OrderApiClient apiClient = new OrderApiClient();
    Order order;
    String trackId;
    String nullTrack = "";
    String wrongTrack = "79877863";

    @Before
    public void setUp() {
        this.order = new Order(
                "Ivan",
                "Ivanych",
                "Some address",
                "4",
                "+79999999999",
                2,
                "2020-06-06T00:00:00.000Z",
                "comment text");

        trackId = this.apiClient.createOrderReturnTrackId(this.order);
    }

    @Test
    @DisplayName("succes request test")
    public void succesRequestTest() {
        Response response = this.apiClient.getOrderByTrackId(this.trackId);
        response.then().statusCode(200).and().body("order.firstName", equalTo(this.order.firstName)).body("order.lastName", equalTo(this.order.lastName)).body("order.address", equalTo(this.order.address)).body("order.phone", equalTo(this.order.phone)).body("order.rentTime", equalTo(this.order.rentTime)).body("order.deliveryDate", equalTo(this.order.deliveryDate)).body("order.comment", equalTo(this.order.comment)).body("order.metroStation", equalTo(this.order.metroStation));
    }

    @Test
    @DisplayName("request without track test")
    public void requestWithoutTrackTest() {
        Response response = this.apiClient.getOrderByTrackId(nullTrack);
        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("request with wrong track test")
    public void requestWithWrongTrackTest() {
        Response response = this.apiClient.getOrderByTrackId(wrongTrack);
        response.then().statusCode(404).and().body("message", equalTo("Заказ не найден"));
    }
}