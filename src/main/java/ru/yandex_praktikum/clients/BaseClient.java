package ru.yandex_praktikum.clients;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    private static final String QA_SCOOTER_BASE_URI = "http://qa-scooter.praktikum-services.ru";
    @Step("Метод возвращающий базовую спецификацию для всех запросов")
    protected RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(QA_SCOOTER_BASE_URI)
                .build();
    }
}
