package ru.yandex_praktikum.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex_praktikum.pojo.*;

import static io.restassured.RestAssured.given;

public class CourierClient extends  BaseClient {
    private static final String CREATE_COURIER_HANDLE = "/api/v1/courier";
    private static final String LOGIN_COURIER_HANDLE = "/api/v1/courier/login";

    private static final String DELETE_COURIER = "/api/v1/courier/{id}";
    @Step("Создание курьера со всеми обязательными параметрами")
    public ValidatableResponse create(CreateCourierRequest createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER_HANDLE)
                .then();
    }

    @Step("Создание заказа без параметра пароль")
    public ValidatableResponse create(CreateCourierRequestWithoutPass createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER_HANDLE)
                .then();
    }

    @Step("Создание круьера без параметра логин")
    public ValidatableResponse create(CreateCourierRequestWithoutLogin createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER_HANDLE)
                .then();
    }

    @Step("Создание курьера без парметра имя")
    public ValidatableResponse create(CreateCourierRequestWithoutFirstName createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER_HANDLE)
                .then();
    }
    @Step("Логин курьера в системе со всеми обязательными параметрами")
    public ValidatableResponse login(LoginCourierRequest loginCourierRequest){
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post(LOGIN_COURIER_HANDLE)
                .then();
    }
    @Step("Логин курера в системе без указания логина")
    public ValidatableResponse login(LoginCourierRequestWithoutLogin loginCourierRequest){
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post(LOGIN_COURIER_HANDLE)
                .then();
    }
    @Step("Логин курьера в системе без указания пароля")
    public ValidatableResponse login(LoginCourierRequestWithoutPass loginCourierRequest){
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post(LOGIN_COURIER_HANDLE)
                .then();
    }
    @Step("Удаление курьера")
    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())
                .pathParam("id",id)
                .when()
                .delete(DELETE_COURIER)
                .then();
    }
}
