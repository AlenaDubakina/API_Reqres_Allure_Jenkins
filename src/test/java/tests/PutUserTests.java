package tests;

import client.UserClient;
import jdk.jfr.Description;
import models.UpdateUser;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class PutUserTests extends BaseTest {
    UserClient userClient = new UserClient();
    UpdateUser userUpdate = new UpdateUser("morpheus", "zion resident");

    @Test
    @Description("Обновление данных у пользователя")
    public void updateUsertest() {
        userClient.updateRequest("/api/users/2", userUpdate)
                .then()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt", startsWith(LocalDate.now().toString()));
    }
}