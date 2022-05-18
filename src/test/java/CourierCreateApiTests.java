import client.CourierApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Courier;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateApiTests {
    CourierApiClient apiClient = new CourierApiClient();
    Courier courier;

    @After
    public void cleanUp() {
        String courierId = apiClient.getCourierId(this.courier);
        this.apiClient.deleteCourier(courierId);
    }

    @Test
    @DisplayName("create courier test")
    public void testCreateCourier() {
        this.courier = new Courier("Dasha12", "7867", "Dasha");
        Response response = apiClient.createCourier(this.courier);
        response.then().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("create courier with same login test")
    public void testSameLoginCreateCourier() {
        this.courier = new Courier("Dasha17", "786897", "Dasha");
        apiClient.createCourier(this.courier);

        Courier sameLoginCourier = new Courier("Dasha17", "786897", "Dasha");
        Response response = apiClient.createCourier(sameLoginCourier);
        response.then().statusCode(409).and().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("create courier succes status code test")
    public void testStatusCode() {
        this.courier = new Courier("Dasha180", "78677", "Dasha");
        Response response = apiClient.createCourier(this.courier);
        response.then().statusCode(201);
    }
}
