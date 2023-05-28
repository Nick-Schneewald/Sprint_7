package ru.yandex_praktikum.dataproviders;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

import java.time.LocalDate;

public class OrderProvider {
    public static CreateOrderRequest getSpecifiedOrderRequest(String firstName, String lastName, String address,
                                                              String metroStation, String phone, Integer rentTime,
                                                              String deliveryDate, String comment, String[] color) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();

        createOrderRequest.setFirstName(firstName);
        createOrderRequest.setLastName(lastName);
        createOrderRequest.setAddress(address);
        createOrderRequest.setMetroStation(metroStation);
        createOrderRequest.setPhone(phone);
        createOrderRequest.setRentTime(rentTime);
        createOrderRequest.setDeliveryDate(deliveryDate);
        createOrderRequest.setComment(comment);
        createOrderRequest.setColor(color);
        return createOrderRequest;
    }

    public static CreateOrderRequest getRandomCreateOrderRequest(){
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(10));
        createOrderRequest.setLastName(RandomStringUtils.randomAlphabetic(10));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(15));
        createOrderRequest.setMetroStation(RandomStringUtils.randomNumeric(2));
        createOrderRequest.setPhone(RandomStringUtils.randomNumeric(12));
        createOrderRequest.setRentTime(1 + (int)(Math.random() * 7));
        createOrderRequest.setDeliveryDate(LocalDate.now().toString());
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(20));
        createOrderRequest.setColor(new String[] {"BLACK","GREY"});
        return createOrderRequest;
    }
}
