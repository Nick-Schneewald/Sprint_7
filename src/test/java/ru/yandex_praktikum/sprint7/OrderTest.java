package ru.yandex_praktikum.sprint7;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex_praktikum.clients.OrderClient;
import ru.yandex_praktikum.clients.UtilsClient;
import ru.yandex_praktikum.dataproviders.OrderProvider;
import ru.yandex_praktikum.pojo.CreateOrderRequest;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {

    private OrderClient orderClient = new OrderClient();
    private UtilsClient utilsClient = new UtilsClient();

    @Parameterized.Parameter(0)
    public String firstName;

    @Parameterized.Parameter(1)
    public String lastName;

    @Parameterized.Parameter(2)
    public String address;

    @Parameterized.Parameter(3)
    public String metroStation;

    @Parameterized.Parameter(4)
    public String phone;

    @Parameterized.Parameter(5)
    public Integer rentTime;

    @Parameterized.Parameter(6)
    public String deliveryDate;

    @Parameterized.Parameter(7)
    public String comment;

    @Parameterized.Parameter(8)
    public String[] color;
    @Parameterized.Parameters(name = "{index} Данные заказчика: {0} - имя; {1} - фамилия; {2} - адрес; " +
            "{3} - ближайшее метро; {4} - телефон; {5} - кол-во дней аренды; {6} - дата доставки; " +
            "{7} - комментарий; {8} - предпочитаемые цвета;")
    public static Collection<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {"Naruto","Uchiha","Konoha, 142 apt.","Университет","+7 800 355 35 35",5,"2023-06-06","Saske, come back to Konoha", new String[] {"BLACK"}},
                {"Sherlock","Holmes","Backer st, 221B","Пражская","+7 916 765 89 92",3,"2023-06-06","Common, Watson!", new String[] {"GREY"} },
                {"John","Watson","Backer st, 221B","Пражская","+7 926 753 46 72",2,"2023-06-06", "You are right, as always!", new String[] {"BLACK","GREY"}},
                {"Vasily", "Alibabaevich", "Urzykstan, 137 apt.","Преображенская площадь","+7 800 355 35 35",5,"2020-06-06","Saske, come back to Konoha",null}
        });
    }
    @Test
    @DisplayName("Order should be placed")
    @Description("Parametrized test for creating order with different parameter sets")
    public void orderShouldBePlaced() {
        String number = utilsClient.searchStation(metroStation)
                .statusCode(200)
                .extract().jsonPath().get("[0].number");
        String stationNumber = number;

        CreateOrderRequest createOrderRequest = OrderProvider.getSpecifiedOrderRequest(firstName,lastName,address,
                stationNumber,phone,rentTime,deliveryDate,comment,color);

        int byCreationTrackNumber = orderClient.create(createOrderRequest)
                .statusCode(201)
                .extract().jsonPath().get("track");

        int inOrderTrackNumber = orderClient.get(byCreationTrackNumber)
                .statusCode(200)
                .extract().jsonPath().get("order.track");

        Assert.assertEquals(byCreationTrackNumber,inOrderTrackNumber);
    }
}

