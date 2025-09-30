package models;

import lombok.Data;

@Data
public class LoginBody {
    String userName, password;

    public LoginBody(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}