package com.afokeeva.table;

public class UserData {

    private String mId;
    private String mName;
    private String mLogin;
    private String mPassword;

    public UserData(String name, String login, String password) {
        mName = name;
        mLogin = login;
        mPassword = password;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
