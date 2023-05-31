package ru.yandex_praktikum.pojo;

public class LoginCourierRequestWithoutPass {
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LoginCourierRequestWithoutPass(String login) {
        this.login = login;
    }

    public static LoginCourierRequestWithoutPass from(CreateCourierRequest createCourierRequest){
        return new LoginCourierRequestWithoutPass(createCourierRequest.getLogin());
    }
}
