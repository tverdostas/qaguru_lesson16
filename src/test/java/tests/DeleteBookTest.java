package tests;

import models.AddBookBody;
import models.UserLoginResponse;
import models.UserResponse;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import steps.ApiSteps;

import java.util.List;

import static com.codeborne.selenide.Selenide.confirm;
import static org.hamcrest.MatcherAssert.assertThat;
import static tests.TestData.username;

public class DeleteBookTest extends BaseTest {
    @Test
    @Tag("")
    @DisplayName("")
    void successfulDeleteBookTest() {

        ApiSteps apiSteps = new ApiSteps();
        ProfilePage profilePage = new ProfilePage();

        UserLoginResponse response = apiSteps.login();

        apiSteps.cleanBookList(response.getToken(), response.getUserId());

        AddBookBody bookInfo = new AddBookBody(response.getUserId(),
                List.of(new AddBookBody.BookIsbn("9781449325862")));

        ApiSteps.addBook(bookInfo, response.getToken());

        ApiSteps.addCookies(response);

        ProfilePage.openPage()
                .removeAdds()
                .checkUserName(username)
                .clickOnDeleteBtn()
                .clickOkInModal();
        confirm();
        profilePage.checkListOfBooksIsEmpty();

        UserResponse userResponse = ApiSteps.userInfo(response.getToken(), response.getUserId());

        assertThat(userResponse.getBooks(),
                Matchers.empty());
    }
    }