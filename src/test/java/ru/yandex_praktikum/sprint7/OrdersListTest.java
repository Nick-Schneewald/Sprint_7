package ru.yandex_praktikum.sprint7;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex_praktikum.clients.CourierClient;
import ru.yandex_praktikum.clients.OrderClient;
import ru.yandex_praktikum.dataproviders.CourierProvider;
import ru.yandex_praktikum.dataproviders.OrderProvider;
import ru.yandex_praktikum.dataproviders.OrdersListProvider;
import ru.yandex_praktikum.pojo.CreateCourierRequest;
import ru.yandex_praktikum.pojo.CreateOrderRequest;
import ru.yandex_praktikum.pojo.GetOrdersListRequest;
import ru.yandex_praktikum.pojo.LoginCourierRequest;

import java.util.List;

public class OrdersListTest {
    private OrderClient orderClient = new OrderClient();
    private CourierClient courierClient = new CourierClient();
    private Integer courierId;
    private int orderId;

    @Test
    @DisplayName("Orders list should be returned with no parameters provided")
    @Description("Basic test for endpoint /api/v1/orders endpoint - without parameters")
    public void ordersListShouldBeReturnedWithNoParamsProvided(){
        OrderClient orderClient = new OrderClient();
        orderClient.getOrdersList()
                .statusCode(200)
                .body("orders",Matchers.any(List.class));
    }

    @Test
    @DisplayName("Orders list should be returned with all parameters provided")
    @Description("Test sends request to /api/v1/orders endpoint - with parameters limit, page, nearestStations")
    public void ordersListShouldBeReturnedWithAllParamsProvided(){
        GetOrdersListRequest getOrdersListRequest = OrdersListProvider.getRandomGetOrdersListRequest();

        orderClient.getOrdersList(getOrdersListRequest)
                .statusCode(200)
                .body("orders",Matchers.any(List.class));
    }

    @Test
    @DisplayName("Orders list should be returned for provided courier with no orders")
    @Description("Test sends request to /api/v1/orders endpoint - with courier id without orders accepted")
    public void ordersListShouldBeReturnedForProvidedCourierWithNoOrders(){
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);
        courierId = courierClient.login(loginCourierRequest)
                .extract().jsonPath().get("id");

        OrderClient orderClient = new OrderClient();
        orderClient.getOrdersList(courierId)
                .statusCode(200)
                .body("orders",Matchers.any(List.class));
    }

    @Test
    @DisplayName("Orders list should be returned for provided courier with order")
    @Description("Test sends request to /api/v1/orders endpoint - with courier id with accepted order")
    public void ordersListShouldBeReturnedForProvidedCourierWithOrder(){
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);
        courierId = courierClient.login(loginCourierRequest)
                .extract().jsonPath().get("id");

        CreateOrderRequest createOrderRequest = OrderProvider.getRandomCreateOrderRequest();
        Integer trackNumber = orderClient.create(createOrderRequest)
                .extract().jsonPath().get("track");

        orderId = orderClient.get(trackNumber)
                .extract().jsonPath().get("order.id");

        orderClient.accept(orderId,courierId);

        orderClient.getOrdersList(courierId)
                .statusCode(200)
                .body("orders",Matchers.any(List.class));
    }

    @Test
    @DisplayName("Orders list should be returned for provided courier with order and all parameters provided")
    @Description("Test sends request to /api/v1/orders endpoint - with courier id with accepted order and with parameters: limit, page, nearestStations")
    public void ordersListShouldBeReturnedForProvidedCourierWithOrderAndAllParamsProvided(){
        //создать запрос курера
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);
        //выполнить логин и получить id
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);
        courierId = courierClient.login(loginCourierRequest)
                .extract().jsonPath().get("id");
        //создать запрос заказа и получть трек-номер заказа
        CreateOrderRequest createOrderRequest = OrderProvider.getRandomCreateOrderRequest();
        Integer trackNumber = orderClient.create(createOrderRequest)
                .extract().jsonPath().get("track");
        //получить id заказа
        orderId = orderClient.get(trackNumber)
                .extract().jsonPath().get("order.id");
        //принять заказ orderId курьером courierId
        orderClient.accept(orderId,courierId);
        //создать запрос на получение списка заказов с произвольными параметрами
        GetOrdersListRequest getOrdersListRequest = OrdersListProvider.getRandomGetOrdersListRequest();
        //получить список заказов для курьера с произвольными параметрами вывода
        orderClient.getOrdersList(courierId, getOrdersListRequest)
                .statusCode(200)
                .body("orders",Matchers.any(List.class));
    }

    @Test
    @DisplayName("Error message should be returned for too long courier id")
    @Description("Test checks that error message appears for too long courier id")
    public void ErrorMessageShouldBeReturnedForTooLongCourierId(){
        courierId = Integer.parseInt(RandomStringUtils.randomNumeric(8));
        orderClient.getOrdersList(courierId)
                .statusCode(404)
                .body("message",Matchers.equalTo("Курьер с идентификатором " + courierId +" не найден"));
        courierId = null;
    }

    @Test
    @DisplayName("Error message should be returned for too short courier id")
    @Description("Test checks that error message appears for too short courier id")
    public void ErrorMessageShouldBeReturnedForTooShortCourierId(){
        courierId = Integer.parseInt(RandomStringUtils.randomNumeric(2));
        orderClient.getOrdersList(courierId)
                .statusCode(404)
                .body("message",Matchers.equalTo("Курьер с идентификатором " + courierId +" не найден"));
        courierId = null;
    }

    @After
    public void tearDown(){
        if(courierId != null) { courierClient.delete(courierId); }
    }
}
