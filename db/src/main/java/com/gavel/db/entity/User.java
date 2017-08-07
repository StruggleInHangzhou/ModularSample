package com.gavel.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jhhuang on 2017/8/7.
 * QQ:781913268
 * Description：xxx
 */
@Entity
public class User
{
    @Id
    private String userId;

    private String userType;

    private String mobile;

    private String password;

    private String userName;

    private String nickName;

    private String token;

    @Generated(hash = 892684959)
    public User(String userId, String userType, String mobile, String password,
            String userName, String nickName, String token) {
        this.userId = userId;
        this.userType = userType;
        this.mobile = mobile;
        this.password = password;
        this.userName = userName;
        this.nickName = nickName;
        this.token = token;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
