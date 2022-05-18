import client.OrderApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {
    OrderApiClient orderApiClient = new OrderApiClient();

    @Parameterized.Parameters
    public static Collection<Order> data() {
        return Arrays.asList(new Order(
                        "Ivan",
                        "Ivanych",
                        "Some address",
                        "4",
                        "+79999999999",
                        2,
                        "2022-06-06",
                        "comment text",
                        new String[]{"BLACK"}
                ),
                new Order(
                        "Ivan",
                        "Ivanych",
                        "Some address",
                        "4",
                        "+79999999999",
                        2,
                        "2022-06-06",
                        "comment text",
                        new String[]{"GREY"}
                ),
                new Order(
                        "Ivan",
                        "Ivanych",
                        "Some address",
                        "4",
                        "+79999999999",
                        2,
                        "2022-06-06",
                        "comment text",
                        new String[]{"BLACK", "GREY"}
                ),
                new Order(
                        "Ivan",
                        "Ivanych",
                        "Some address",
                        "4",
                        "+79999999999",
                        2,
                        "2022-06-06",
                        "comment text"
                ));
    }

    Order order;

    public OrderTest(Order order) {
        this.order = order;
    }

    @Test
    @DisplayName("Create order test")
    public void testCreateOrder() {
        Response response = this.orderApiClient.createOrder(this.order);
        response.then().statusCode(201).and().body("track", notNullValue());
    }
}
