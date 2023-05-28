package ru.yandex_praktikum.dataproviders;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex_praktikum.pojo.CreateCourierRequest;
import ru.yandex_praktikum.pojo.GetOrdersListRequest;

public class OrdersListProvider {
    private static final int NUMBER_OF_STATIONS_OVERALL = 237;
    private static final int MAX_NUMBER_OF_NEAREST_STATIONS = 3;
    public static GetOrdersListRequest getRandomGetOrdersListRequest(){
        GetOrdersListRequest getOrdersListRequest = new GetOrdersListRequest();

        int arrayDim = 1 + (int)(Math.random() * MAX_NUMBER_OF_NEAREST_STATIONS);
        String[] nearestStations = new String[arrayDim];
        int randomStationNumber = (1 + (int)(Math.random() * NUMBER_OF_STATIONS_OVERALL));
        for(int idx = 0; idx < arrayDim; idx++, randomStationNumber++) {nearestStations[idx] = String.valueOf(randomStationNumber);}

        getOrdersListRequest.setNearestStations(nearestStations);
        getOrdersListRequest.setLimit(1 + (int) (Math.random() * 30));
        getOrdersListRequest.setPage(1 + (int) (Math.random() * 10));
        return getOrdersListRequest;
    }
}
