package ru.yandex_praktikum.clients;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UtilsClient extends BaseClient{
    public ValidatableResponse searchStation(String station){
        return given()
                .spec(getSpec())
                .pathParam("s",station)
                .when()
                .get("api/v1/stations/search?s={s}")
                .then();
    }
}
