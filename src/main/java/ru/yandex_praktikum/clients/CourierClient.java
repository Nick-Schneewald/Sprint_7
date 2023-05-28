package ru.yandex_praktikum.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex_praktikum.pojo.*;

import static io.restassured.RestAssured.given;

public class CourierClient extends  BaseClient {
    public ValidatableResponse create(CreateCourierRequest createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    public ValidatableResponse create(CreateCourierRequestWithoutPass createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    public ValidatableResponse create(CreateCourierRequestWithoutLogin createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    public ValidatableResponse create(CreateCourierRequestWithoutFirstName createCourierRequest){
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    public ValidatableResponse login(LoginCourierRequest loginCourierRequest){
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse login(LoginCourierRequestWithoutLogin loginCourierRequest){
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse login(LoginCourierRequestWithoutPass loginCourierRequest){
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())
                .pathParam("id",id)
                .when()
                .delete("/api/v1/courier/{id}")
                .then();
    }
}
