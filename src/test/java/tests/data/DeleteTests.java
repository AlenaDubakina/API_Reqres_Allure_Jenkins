package tests.data;

import client.UserClient;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import tests.BaseTest;

public class DeleteTests extends BaseTest {

    UserClient userClient = new UserClient();

    @Test
    @Description("Удаление пользователя позитивный тест")
    public void deletePositiveTest() {
        userClient.deleteRequest("/api/users/2")
                .then()
                .statusCode(204);
    }
}