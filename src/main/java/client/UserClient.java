package client;

import config.ApiConfig;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Login;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Отправить get запрос '{endpoint}'")
    public Response getRequest(String endpoint) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .when()
                .get(endpoint);
    }

    @Step("Отправить post запрос с пользователем '{newUser.email}'")
    public Response postRequest(String endpoint, Login newUser) {
        return given()
                .spec(ApiConfig.requestSpecBuilder)
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post(endpoint);
    }
}