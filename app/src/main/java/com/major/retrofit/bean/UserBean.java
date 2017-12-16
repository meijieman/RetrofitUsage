package com.major.retrofit.bean;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/12/17 0:02
 */
public class UserBean {

    private String username;
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserBean{" +
               "username='" + username + '\'' +
               ", pwd='" + pwd + '\'' +
               '}';
    }
}
