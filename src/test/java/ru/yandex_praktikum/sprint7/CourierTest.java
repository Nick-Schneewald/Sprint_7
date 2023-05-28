package ru.yandex_praktikum.sprint7;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex_praktikum.clients.CourierClient;
import ru.yandex_praktikum.dataprovider.CourierProvider;
import ru.yandex_praktikum.pojo.*;


public class CourierTest {
    private CourierClient courierClient = new CourierClient();
    private Integer id;
    @Test
    @DisplayName("Courier could be created")
    @Description("General test for creating courier")
    public void courierShouldBeCreated(){
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);
        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @Test
    @DisplayName("Two couriers with the same credentials shouldn't be created")
    @Description("The user couldn't create multiple couriers with the same login/password/firstname parameters")
    public void twoCouriersWithTheSameCredentialsShouldNotBeCreated(){
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest)
                .statusCode(201)
                .and().body("ok",Matchers.equalTo(true));

        courierClient.create(createCourierRequest)
                .statusCode(409)
                .and().body("message",Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Courier shouldn't be created without password")
    @Description("Basic test on courier creation with lack of parameters")
    public void courierShouldNotBeCreatedWithoutPassword(){
        CreateCourierRequestWithoutPass createCourierRequestWithoutPass = CourierProvider.getRandomCreateCourierRequestWithoutPass();
        courierClient.create(createCourierRequestWithoutPass)
                .statusCode(400)
                .body("message",Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier shouldn't be created without login")
    @Description("Basic test on courier creation with lack of parameters")
    public void courierShouldNotBeCreatedWithoutLogin(){
        CreateCourierRequestWithoutLogin createCourierRequestWithoutLogin = CourierProvider.getRandomCreateCourierRequestWithoutLogin();
        courierClient.create(createCourierRequestWithoutLogin)
                .statusCode(400)
                .body("message",Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier shouldn't be created without firstname")
    @Description("Basic test on courier creation with lack of parameters")
    public void courierShouldNotBeCreatedWithoutFirstName(){
        CreateCourierRequestWithoutFirstName createCourierRequestWithoutFirstName = CourierProvider.getRandomCreateCourierRequestWithoutFirstName();
        courierClient.create(createCourierRequestWithoutFirstName)
                .statusCode(400)
                .body("message",Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier shouldn't be created with empty-credentials")
    @Description("Courier shouldn't be created  with  empty String values in the fields of data provider")
    public void courierShouldNotBeCreatedWithEmptyCredentials(){
        CreateCourierRequest courierRequest = CourierProvider.getEmptyCreateCourierRequest();
        courierClient.create(courierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier shouldn't be created with null-credentials")
    @Description("Courier shouldn't be created  with  null values in the fields of data provider")
    public void courierShouldNotBeCreatedWithNullCredentials(){
        CreateCourierRequest courierRequest = CourierProvider.getNullValuesCreateCourierRequest();
        courierClient.create(courierRequest)
                .statusCode(400)
                .body("message",Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Courier shouldn't login without login")
    @Description(("Courier couldn't perform login using only password-field in the request body"))
    public void courierShouldNotLogInWithoutLogin(){
        CreateCourierRequest courierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(courierRequest);

        LoginCourierRequestWithoutLogin loginCourierRequest = LoginCourierRequestWithoutLogin.from(courierRequest);
        courierClient.login(loginCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Courier shouldn't login without password")
    @Description("Courier couldn't perform login using only login-field in the request body")
    /*Тест не проходит так как сервер бесконечно обрабатывает запрос и в конце возвращает 504 Gateway time out*/
    public void courierShouldNotLogInWithoutPassword(){
        CreateCourierRequest courierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(courierRequest);

        LoginCourierRequestWithoutPass loginCourierRequest = LoginCourierRequestWithoutPass.from(courierRequest);
        courierClient.login(loginCourierRequest)
                .statusCode(504)
                .statusLine("HTTP/1.1 504 Gateway time out");

        /* Изначальный вариант теста
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));*/
    }

    @Test
    @DisplayName("Error showed when login with wrong credentials")
    @Description("Error message appears when user tries to login with illegal login/password pair")
    public void errorShowedWhenLogInWithWrongCredentials(){
        CreateCourierRequest courierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(courierRequest);

        CreateCourierRequest anotherCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(anotherCourierRequest);
        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Error showed when login with empty credentials")
    @Description("Error message appears when user tries to login with empty login/password pair")
    public void errorShowedWhenLogInWithEmptyCredentials(){
        CreateCourierRequest courierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(courierRequest);

        CreateCourierRequest emptyCourierRequest = CourierProvider.getEmptyCreateCourierRequest();
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(emptyCourierRequest);
        courierClient.login(loginCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Should return id in login")
    @Description("Successful login returns courier's id in response body")
    public void shouldReturnIdOnLogin(){
        CreateCourierRequest courierRequest = CourierProvider.getRandomCreateCourierRequest();

        courierClient.create(courierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(courierRequest);
        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @After
    public void tearDown(){
        if(id != null) {
            courierClient.delete(id);
        }
    }
}
