package tests;

import client.UserClient;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.data.TestData;

import static org.hamcrest.Matchers.equalTo;

public class CreateTests extends BaseTest {

    private UserClient userClient = new UserClient();

    @Test
    @Description("Регистрация пользователя позитивный тест")
    public void createRegisterPositiveTest() {
        userClient.postRequest("/api/register", TestData.VALID_USER)
                .then()
                .body("id", equalTo(4))
                .body("token", equalTo(TestData.TOKEN));
    }

    @Test
    @Description("Вход пользователя позитивный тест")
    public void createLoginPositiveTest() {

        String token = userClient.postRequest("/api/login", TestData.VALID_USER)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("token");

        Assert.assertNotNull(token, "Токен не вернулся в ответе");
        Assert.assertTrue(token.equals(TestData.TOKEN), "Токен отличается от ожидаемого");
    }

    @Test
    @Description("Регистрация невалидного пользователя негативный тест")
    public void createLoginNegativeTest() {
        userClient.postRequest("/api/login", TestData.INVALID_USER)
                .then()
                .statusCode(400)
                .body("error", equalTo(TestData.ERROR_MESSAGE));
    }

    @Test
    @Description("Вход невалидного пользователя негативный тест")
    public void createRegisterNegativeTest() {

        userClient.postRequest("/api/register", TestData.INVALID_USER)
                .then()
                .statusCode(400)
                .body("error", equalTo(TestData.ERROR_MESSAGE));
    }
}