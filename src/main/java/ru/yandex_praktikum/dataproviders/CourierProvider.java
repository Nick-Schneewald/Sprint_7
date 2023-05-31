package ru.yandex_praktikum.dataproviders;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex_praktikum.pojo.CreateCourierRequest;
import ru.yandex_praktikum.pojo.CreateCourierRequestWithoutFirstName;
import ru.yandex_praktikum.pojo.CreateCourierRequestWithoutLogin;
import ru.yandex_praktikum.pojo.CreateCourierRequestWithoutPass;

public class CourierProvider {
    public static CreateCourierRequest getRandomCreateCourierRequest(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));
        return createCourierRequest;
    }

    public static CreateCourierRequestWithoutPass getRandomCreateCourierRequestWithoutPass(){
        CreateCourierRequestWithoutPass createCourierRequest = new CreateCourierRequestWithoutPass();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));
        return createCourierRequest;
    }

    public static CreateCourierRequestWithoutLogin getRandomCreateCourierRequestWithoutLogin(){
        CreateCourierRequestWithoutLogin createCourierRequest = new CreateCourierRequestWithoutLogin();
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));
        return createCourierRequest;
    }

    public static CreateCourierRequestWithoutFirstName getRandomCreateCourierRequestWithoutFirstName(){
        CreateCourierRequestWithoutFirstName createCourierRequest = new CreateCourierRequestWithoutFirstName();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        return createCourierRequest;
    }

    public static CreateCourierRequest getEmptyCreateCourierRequest(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin("");
        createCourierRequest.setPassword("");
        createCourierRequest.setFirstName("");
        return createCourierRequest;
    }

    public static CreateCourierRequest getNullValuesCreateCourierRequest(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(null);
        createCourierRequest.setPassword(null);
        createCourierRequest.setFirstName(null);
        return createCourierRequest;
    }

}
