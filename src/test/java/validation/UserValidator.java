package validation;

import io.qameta.allure.Step;
import models.User;
import org.testng.asserts.SoftAssert;

public class UserValidator {

    @Step("Проверка данных у пользователя")
    public static void validateUser(User user) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(user.getId(),
                "Поле id null у " + user);
        softAssert.assertTrue(user.getId() >= 0,
                ("Поле id меньше или равно 0 у '%s'").formatted(user));
        softAssert.assertTrue(user.getEmail().contains("reqres.in"),
                "Некорректное поле email у " + user);
        softAssert.assertTrue(user.getEmail().contains(user.getFirst_name().toLowerCase()),
                "Некорректное поле email у " + user);
        softAssert.assertTrue(user.getAvatar().contains("reqres.in"),
                "Некорректное поле avatar у " + user);
        softAssert.assertTrue(user.getAvatar().contains(String.valueOf(user.getId())),
                "Некорректное поле avatar у " + user);
        softAssert.assertAll();
    }
}