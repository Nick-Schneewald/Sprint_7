package ru.yandex_praktikum.clients;

import io.restassured.response.ValidatableResponse;
import ru.yandex_praktikum.pojo.CreateOrderRequest;
import ru.yandex_praktikum.pojo.GetOrdersListRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient{
    public ValidatableResponse create(CreateOrderRequest createOrderRequest){
        return given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    public ValidatableResponse get(int trackNumber){
        return given()
                .spec(getSpec())
                .pathParam("track",trackNumber)
                .when()
                .get("/api/v1/orders/track?t={track}")
                .then();
    }

    public ValidatableResponse getOrdersList(){
        return given()
                .spec(getSpec())
                .when()
                .get("/api/v1/orders")
                .then();
    }

    public ValidatableResponse getOrdersList(GetOrdersListRequest getOrdersListRequest){
        return given()
                .spec(getSpec())
                .pathParam("nearestStation",getOrdersListRequest.getNearestStations())
                .pathParam("limit", getOrdersListRequest.getLimit())
                .pathParam("page", getOrdersListRequest.getPage())
                .when()
                .get("/api/v1/orders?limit={limit}&page={page}&nearestStation={nearestStation}")
                .then();
    }

    public ValidatableResponse getOrdersList(Integer courierId){
        return given()
                .spec(getSpec())
                .pathParam("courierId", courierId)
                .when()
                .get("/api/v1/orders?courierId={courierId}")
                .then();
    }

    public ValidatableResponse getOrdersList(Integer courierId, GetOrdersListRequest getOrdersListRequest){
        return given()
                .spec(getSpec())
                .pathParam("courierId", courierId)
                .pathParam("nearestStation",getOrdersListRequest.getNearestStations())
                .pathParam("limit", getOrdersListRequest.getLimit())
                .pathParam("page", getOrdersListRequest.getPage())
                .when()
                .get("/api/v1/orders?courierId={courierId}&limit={limit}&page={page}&nearestStation={nearestStation}")
                .then();
    }

    public ValidatableResponse accept(Integer orderId, Integer courierId){
        return given()
                .spec(getSpec())
                .pathParams("id",orderId,"courierId",courierId)
                .when()
                .put("/api/v1/orders/accept/{id}?courierId={courierId}")
                .then();
    }
}
