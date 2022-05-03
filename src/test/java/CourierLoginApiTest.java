import client.CourierApiClient;
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
        int courierId = this.apiClient.getCourierId(this.courier);
        this.apiClient.deleteCourier(courierId);
    }

    @Test
    public void testAuthorization() {
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(200).and().body("id", notNullValue());
    }

    @Test
    public void testAuthorizationWithoutPassword() {
        this.courier.password = "";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void testAuthorizationWithoutLogin() {
        this.courier.login = "";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void testAuthorizationWithWrongPassword() {
        this.courier.password = "00000";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void testAuthorizationWithWrongLogin() {
        this.courier.login = "Wronglogin";
        Response response = this.apiClient.authorizationCourier(this.courier);

        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }
}
