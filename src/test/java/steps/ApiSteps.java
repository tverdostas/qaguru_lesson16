package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ApiSteps {
/*    @Step("Отправить запрос на авторизацию")


    @Step("Добавить куки в запрос")*/
    public void addCookie(String userId, String username, String token){
        getWebDriver().manage().addCookie(new Cookie(userId, "1621356c-0640-455d-86fa-fe7fc292d0a8"));
        getWebDriver().manage().addCookie(new Cookie(username, "elka"));
        getWebDriver().manage().addCookie(new Cookie(token, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImVsa2EiLCJwYXNzd29yZCI6IlFXRSVxd2UxMjMiLCJpYXQiOjE3NTg1NjYwMjB9.JjzUD4OFY10HH7ODEKA4NVJgU5aaZ3hj_s7f373Yjj0"));
    }
}
