package ru.yandex_praktikum.pojo;

public class LoginCourierRequestWithoutLogin {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginCourierRequestWithoutLogin(String password) {
        this.password = password;
    }

    public static LoginCourierRequestWithoutLogin from(CreateCourierRequest createCourierRequest){
        return new LoginCourierRequestWithoutLogin(createCourierRequest.getPassword());
    }
}
