package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    public static RequestSpecification requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(ConfigReader.getProperties("base.uri"))
            .addHeader("x-api-key", ConfigReader.getProperties("api.key"))
            .build();
}