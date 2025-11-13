package tests.data;

import models.Login;

public class TestData {
    public static final Login VALID_USER = new Login("eve.holt@reqres.in", "cityslicka");
    public static final Login INVALID_USER = new Login("eve.holt@reqres.in", null);
    public static final String TOKEN = "QpwL5tke4Pnpja7X4";
    public static final String ERROR_MESSAGE = "Missing password";
}