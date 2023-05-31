package ru.yandex_praktikum.pojo;

public class GetOrdersListRequest {
    private String[] nearestStations;
    private int limit;
    private int page;

    public String[] getNearestStations() {
        return nearestStations;
    }

    public void setNearestStations(String[] nearestStations) {
        this.nearestStations = nearestStations;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
