import client.CourierApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Courier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierTest {

    CourierApiClient apiClient = new CourierApiClient();
    Courier courier;
    String courierId;

    @Before
    public void setUp() {
        this.courier = new Courier(
                "Swan",
                "password1234",
                "Testfirstname"
        );

        this.apiClient.createCourier(this.courier);
        this.courierId = this.apiClient.getCourierId(this.courier);
    }

    @Test
    @DisplayName("delete courier true test")
    public void testDeleteCourierTrue() {
        Response response = this.apiClient.deleteCourier(this.courierId);
        response.then().statusCode(200).and().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("delete courier whit not existed id test")
    public void testDeleteCourierWhenIdIsNotExist() {
        Response response = this.apiClient.deleteCourier("17293877");
        response.then().statusCode(404).and().body("message", equalTo("Курьера с таким id нет."));
    }

    @Test
    @DisplayName("delete courier without id")
    public void testRequestWitoutId() {
        Response response = this.apiClient.deleteCourier("");
        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для удаления курьера"));
    }

}

