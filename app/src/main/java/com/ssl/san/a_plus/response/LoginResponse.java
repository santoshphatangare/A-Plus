package com.ssl.san.a_plus.response;

import com.ssl.san.a_plus.beans.UserBean;

/**
 * Created by Santosh on 11-Jan-18.
 */

public class LoginResponse extends Response {
    UserBean user = new UserBean();

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
