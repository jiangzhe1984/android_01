package com.example.jiangz.entity;

/**
 * Created by JiangZ on 2015-08-25.
 */
public class Userinfo{

    private Integer id;

    private String username;

    private String password;

    private Integer isEnable;

    private Integer isLock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
