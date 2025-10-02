package tests;

import helpers.WithLogin;
import models.AddBookBody;
import models.UserLoginResponse;
import models.UserResponse;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import steps.BooksSteps;
import steps.LoginSteps;

import java.util.List;

import static com.codeborne.selenide.Selenide.confirm;
import static org.hamcrest.MatcherAssert.assertThat;
import static tests.TestData.username;

public class DeleteBookTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    BooksSteps booksSteps = new BooksSteps();
    ProfilePage profilePage = new ProfilePage();
    UserLoginResponse userLoginResponse;

    @WithLogin
    @Test
    @DisplayName("Успешное добавление книги в список")
    void successfulDeleteBookTest() {

        userLoginResponse = loginSteps.login();

        booksSteps.cleanBookList(userLoginResponse.getToken(), userLoginResponse.getUserId());

        AddBookBody bookInfo = new AddBookBody(userLoginResponse.getUserId(),
                List.of(new AddBookBody.BookIsbn("9781449325862")));

        booksSteps.addBook(bookInfo, userLoginResponse.getToken());

        ProfilePage.openPage()
                .removeAdds()
                .checkUserName(username)
                .clickOnDeleteBtn()
                .clickOkInModal();
        confirm();
        profilePage.checkListOfBooksIsEmpty();

        UserResponse userResponse = LoginSteps.userInfo(userLoginResponse.getToken(), userLoginResponse.getUserId());

        assertThat(userResponse.getBooks(),
                Matchers.empty());
    }
}