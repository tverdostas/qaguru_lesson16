package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.RequestSpec.request;
import static specs.ResponseSpec.responseSpec;

public class DeleteBookTest extends BaseTest {
    @Test
    @Tag("api")
    @DisplayName("При авторизации ответ с токеном не пустой")
    void successfulDeleteBookTest() {
        String authData = "{\"userName\":\"elka\",\"password\":\"QWE%qwe123\"}";

        Response response =
                given()
                        .spec(request)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec(200))
                        .extract().response();

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("userId", response.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("username", response.path("username")));
        getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));
    }
}
