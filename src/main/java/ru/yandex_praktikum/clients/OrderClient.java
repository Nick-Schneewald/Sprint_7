package ru.yandex_praktikum.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex_praktikum.pojo.CreateOrderRequest;
import ru.yandex_praktikum.pojo.GetOrdersListRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient{
    private static final String CREATE_ORDER_HANDLE = "/api/v1/orders";
    private static final String GET_ORDERS_LIST_HANDLE = "/api/v1/orders";
    private static final String ACCEPT_ORDER_HANDLE = "/api/v1/orders/accept/{id}";
    private static final String GET_ORDER_BY_TRACK_NUM_HANDLE = "/api/v1/orders/track";
    @Step("Создать заказ")
    public ValidatableResponse create(CreateOrderRequest createOrderRequest){
        return given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post(CREATE_ORDER_HANDLE)
                .then();
    }
    @Step("Получить заказ по его номеру")
    public ValidatableResponse get(int trackNumber){
        return given()
                .spec(getSpec())
                .pathParam("track",trackNumber)
                .when()
                .get(GET_ORDER_BY_TRACK_NUM_HANDLE + "?t={track}")
                .then();
    }
    @Step("Получить список заказов без параметров")
    public ValidatableResponse getOrdersList(){
        return given()
                .spec(getSpec())
                .when()
                .get(GET_ORDERS_LIST_HANDLE)
                .then();
    }
    @Step("Получить список заказов по параметрам nearestStation, limit, page")
    public ValidatableResponse getOrdersList(GetOrdersListRequest getOrdersListRequest){
        return given()
                .spec(getSpec())
                .pathParam("nearestStation",getOrdersListRequest.getNearestStations())
                .pathParam("limit", getOrdersListRequest.getLimit())
                .pathParam("page", getOrdersListRequest.getPage())
                .when()
                .get(GET_ORDERS_LIST_HANDLE + "?limit={limit}&page={page}&nearestStation={nearestStation}")
                .then();
    }
    @Step("Получить список заказов по id курера")
    public ValidatableResponse getOrdersList(Integer courierId){
        return given()
                .spec(getSpec())
                .pathParam("courierId", courierId)
                .when()
                .get(GET_ORDERS_LIST_HANDLE + "?courierId={courierId}")
                .then();
    }
    @Step("Получить список заказов по полному списку параметров: courierId, nearestStation, limit, page ")
    public ValidatableResponse getOrdersList(Integer courierId, GetOrdersListRequest getOrdersListRequest){
        return given()
                .spec(getSpec())
                .pathParam("courierId", courierId)
                .pathParam("nearestStation",getOrdersListRequest.getNearestStations())
                .pathParam("limit", getOrdersListRequest.getLimit())
                .pathParam("page", getOrdersListRequest.getPage())
                .when()
                .get(GET_ORDERS_LIST_HANDLE + "?courierId={courierId}&limit={limit}&page={page}&nearestStation={nearestStation}")
                .then();
    }
    @Step("Принять заказ")
    public ValidatableResponse accept(Integer orderId, Integer courierId){
        return given()
                .spec(getSpec())
                .pathParams("id",orderId,"courierId",courierId)
                .when()
                .put(ACCEPT_ORDER_HANDLE + "?courierId={courierId}")
                .then();
    }
}
