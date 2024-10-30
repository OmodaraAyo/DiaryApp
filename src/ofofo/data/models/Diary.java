package ofofo.data.models;

import java.util.ArrayList;
import java.util.List;


public class Diary {
    private String userName;
    private String loginPassword;

    public Diary() {

    }
    public Diary(String userName) {
        this.userName = userName;
    }

    public Diary(String userName, String loginPassword) {
        this.userName = userName;
        this.loginPassword = loginPassword;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Diary{" + "userName: " + userName + ", loginPassword: " + loginPassword + '}';
    }
}
