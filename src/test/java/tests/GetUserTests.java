package tests;

import client.UserClient;
import jdk.jfr.Description;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import validation.UserValidator;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUserTests extends BaseTest {

    public UserClient userClient = new UserClient();

    @Test
    @Description("Запрос списка пользователей позитивный тест")
    public void getUsersTest() {
        List<User> users = userClient.getRequest("/api/users?page=2")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data", User.class);

        users.forEach(UserValidator::validateUser);
    }

    @Test
    @Description("Проверка пользователя позитивный тест")
    public void getUser2Test() {
        User user2 = userClient.getRequest("/api/users/2")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", User.class);

        UserValidator.validateUser(user2);

        Assert.assertEquals(user2.getId(), 2,
                "Некорректное поле id");
        Assert.assertEquals(user2.getEmail(), "janet.weaver@reqres.in",
                "Некорректное поле email");
        Assert.assertEquals(user2.getFirst_name(), "Janet",
                "Некорректное поле first_name");
        Assert.assertEquals(user2.getLast_name(), "Weaver",
                "Некорректное поле last_name");
        Assert.assertEquals(user2.getAvatar(), "https://reqres.in/img/faces/2-image.jpg",
                "Некорректное поле avatar");
    }

    @Test
    @Description("Проверка валидности JSON схемы пользователя")
    public void validateSchemaJsonTest() {
        userClient.getRequest("/api/users/2")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .log().all();
    }

    @Test
    @Description("Проверка запроса пользователя негативный тест")
    public void getUserNotFoundTest() {
        userClient.getRequest("/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    @Description("Проверка времени задержки запроса пользователей позитивный тест")
    public void getDelayedTest() {
        long timeResponse = userClient.getRequest("/api/users?delay=3")
                .then()
                .statusCode(200)
                .extract()
                .time();

        Assert.assertTrue(timeResponse >= 3000,
                "Задержка составила " + timeResponse/ 1000 +"сек.");
    }
}