package steps;

import io.qameta.allure.Step;
import models.AddBookBody;

import static io.restassured.RestAssured.given;
import static specs.SpecsBookStoreTest.baseRequestSpec;
import static specs.SpecsBookStoreTest.bookCollectionResponseSpec;

public class BooksSteps {
    @Step("Отправить запрос на добавление книги в список")
    public static void addBook(AddBookBody bookData, String token) {

        given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(bookCollectionResponseSpec)
                .statusCode(201)
                .extract().response();
    }
    @Step("Отправить запрос на очистку списка книг")
    public static void cleanBookList(String token, String userId) {

        given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(bookCollectionResponseSpec)
                .statusCode(204);
    }
}
