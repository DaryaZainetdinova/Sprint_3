import client.CourierApiClient;
import io.restassured.response.Response;
import models.Courier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateApiTests {
    CourierApiClient apiClient = new CourierApiClient();

    @Test
    public void testCreateCourier() {
        Courier newCourier = new Courier("Dasha12", "7867", "Dasha");
        Response response = apiClient.createCourier(newCourier);
        response.then().body("ok", equalTo(true));

        int courierId = apiClient.getCourierId(newCourier);
        Response deleteResponse = apiClient.deleteCourier(courierId);
        deleteResponse.then().body("ok", equalTo(true));
    }

    @Test
    public void testSameLoginCreateCourier() {
        Courier newCourier = new Courier("Dasha17", "786897", "Dasha");
        apiClient.createCourier(newCourier);

        Courier sameLoginCourier = new Courier("Dasha17", "786897", "Dasha");
        Response response = apiClient.createCourier(sameLoginCourier);
        response.then().statusCode(409).and().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        apiClient.deleteCourier(apiClient.getCourierId(newCourier));
    }

    @Test
    public void testStatusCode() {
        Courier newCourier = new Courier("Dasha180", "78677", "Dasha");
        Response response = apiClient.createCourier(newCourier);
        response.then().statusCode(201);
        apiClient.deleteCourier(apiClient.getCourierId(newCourier));
    }
}
