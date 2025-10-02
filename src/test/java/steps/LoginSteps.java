package steps;

import io.qameta.allure.Step;
import models.AddBookBody;
import models.LoginBody;
import models.UserLoginResponse;
import models.UserResponse;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.SpecsBookStoreTest.*;
import static tests.TestData.password;
import static tests.TestData.username;

public class LoginSteps {
/*@Step("Добавить куки в запрос")
public static void addCookies(UserLoginResponse userLoginResponse) {
    open("/favicon.ico");
    getWebDriver().manage().addCookie(new Cookie("userID", userLoginResponse.getUserId()));
    getWebDriver().manage().addCookie(new Cookie("expires", userLoginResponse.getExpires()));
    getWebDriver().manage().addCookie(new Cookie("token", userLoginResponse.getToken()));
}*/
    @Step("Отправить запрос на логин в систему")
    public static UserLoginResponse login() {
        LoginBody authData = new LoginBody(username, password);
        return given(baseRequestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(baseResponseSpec)
                .extract().as(UserLoginResponse.class);
    }

    @Step("Отправить запрос на получение информации по юзеру")
    public static UserResponse userInfo(String token, String userId) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId)
                .then()
                .spec(baseResponseSpec)
                .extract().as(UserResponse.class);
    }
}
