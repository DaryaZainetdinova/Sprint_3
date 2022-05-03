import client.CourierApiClient;
import io.restassured.response.Response;
import models.Courier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CourierRequiredParamsTests {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Vasia", "password", "" },
                { "Vasia", "", "Недостаточно данных для создания учетной записи" },
                { "", "password", "Недостаточно данных для создания учетной записи" },
        });
    }

    private String login;
    private String password;
    private String errorMessage;
    CourierApiClient apiClient = new CourierApiClient();

    public CourierRequiredParamsTests(String login, String password, String errorMessage) {
        this.login = login;
        this.password = password;
        this.errorMessage = errorMessage;
    }

    @Test
    public void test() {
        Courier courier = new Courier();
        courier.login = this.login;
        courier.password = this.password;
        Response response = apiClient.createCourier(courier);

        if (this.errorMessage.length() == 0) {
            response.then().body("ok", equalTo(true));
            this.apiClient.deleteCourier(this.apiClient.getCourierId(courier));
        } else {
            response.then().body("message", equalTo(this.errorMessage));
        }
    }
}
