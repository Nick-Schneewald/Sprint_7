package ru.yandex_praktikum.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UtilsClient extends BaseClient{

    private static final String SEARCH_STATION_BY_NAME_HANDLE = "api/v1/stations/search";
    @Step("Поиск станции метро по названию")
    public ValidatableResponse searchStation(String station){
        return given()
                .spec(getSpec())
                .pathParam("s",station)
                .when()
                .get(SEARCH_STATION_BY_NAME_HANDLE + "?s={s}")
                .then();
    }
}
