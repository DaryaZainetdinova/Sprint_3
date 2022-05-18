import client.CourierApiClient;
import client.OrderApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Courier;
import models.Order;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderTests {
    OrderApiClient orderApiClient = new OrderApiClient();
    CourierApiClient courierApiClient = new CourierApiClient();
    Courier courier;
    String courierId;
    Order order;
    String orderId;
    String wrongCourierId = "778369";
    String nullCourierId = "";
    String nullOrder = "";
    String wrongOrderId = "14625374";

    @Before
    public void setUp() {
        this.courier = new Courier(
                "Swan",
                "password1234",
                "Testfirstname"
        );

        this.courierApiClient.createCourier(this.courier);
        this.courierId = this.courierApiClient.getCourierId(this.courier);

        this.order = new Order(
                "Ivan",
                "Ivanych",
                "Some address",
                "4",
                "+79999999999",
                2,
                "2022-06-06",
                "comment text");

        this.orderId = this.orderApiClient.createOrderReturnTrackId(this.order);
    }

    @Test
    @DisplayName("accept order succes test")
    public void testAcceptOrderSucces() {
        Response response = this.orderApiClient.acceptOrder(orderId, this.courierId);
        response.then().statusCode(200).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("accept order with wrong courierId  test")
    public void testAcceptOrderWithWrogCourierId() {
        Response response = this.orderApiClient.acceptOrder(orderId, wrongCourierId);
        response.then().statusCode(404).body("message", equalTo("Курьера с таким id не существует"));
    }

    @Test
    @DisplayName("accept order without courierId  test")
    public void testAcceptOrderWithoutCourierId() {
        Response response = this.orderApiClient.acceptOrder(orderId, nullCourierId);
        response.then().statusCode(400).body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("test Accept Order Without OrderId")
    public void testAcceptOrderWithoutOrderId() {
        Response response = this.orderApiClient.acceptOrder(nullOrder, courierId);
        response.then().statusCode(400).body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("test Accept Order With wrong OrderId")
    public void testAcceptOrderWithWrongtOrderId() {
        Response response = this.orderApiClient.acceptOrder(wrongOrderId, courierId);
        response.then().statusCode(404).body("message", equalTo("Заказа с таким id не существует"));
    }
}