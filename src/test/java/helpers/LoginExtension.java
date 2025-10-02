package helpers;

import com.codeborne.selenide.Selenide;
import models.UserLoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import steps.LoginSteps;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    public static UserLoginResponse loginResponse;

    @Override
    public void beforeEach(ExtensionContext context) {
        loginResponse = LoginSteps.login();

        Selenide.open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        refresh();
    }
}
