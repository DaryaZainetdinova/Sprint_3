import client.OrderApiClient;
import models.OrderList;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class OrderListTest {
    OrderApiClient orderApiClient = new OrderApiClient();

    @Test
    public void testOrdersList() {
        OrderList list = this.orderApiClient.getOrders();
        assertTrue(list.orders.length > 0);
    }
}
