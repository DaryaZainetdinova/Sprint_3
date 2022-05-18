import client.CourierApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginApiTest {
    CourierApiClient apiClient = new CourierApiClient();
    Courier courier;

    @Before
    public void setup() {
        this.courier = new Courier(
                "somecourier",
                "password1234",
                "Testfirstname"
        );

        this.apiClient.createCourier(this.courier);
    }

    @After
    public void cleanup() {
        String courierId = this.apiClient.getCourierId(this.courier);
        this.apiClient.deleteCourier(courierId);
    }

    @Test
    @DisplayName("succes autorization test")
    public void testAuthorization() {
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(200).and().body("id", notNullValue());
    }

    @Test
    @DisplayName("autorization without password test")
    public void testAuthorizationWithoutPassword() {
        this.courier.password = "";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("autorization without login test")
    public void testAuthorizationWithoutLogin() {
        this.courier.login = "";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("autorization with wrong password test")
    public void testAuthorizationWithWrongPassword() {
        this.courier.password = "00000";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("autorization with wrong login test")
    public void testAuthorizationWithWrongLogin() {
        this.courier.login = "Wronglogin";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }
}
