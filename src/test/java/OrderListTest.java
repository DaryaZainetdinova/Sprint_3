import client.OrderApiClient;
import io.qameta.allure.junit4.DisplayName;
import models.OrderList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderListTest {
    OrderApiClient orderApiClient = new OrderApiClient();

    @Test
    @DisplayName("Check that orders created")
    public void testOrdersList() {
        OrderList list = this.orderApiClient.getOrders();
        assertTrue(list.orders.length > 0);
    }
}
