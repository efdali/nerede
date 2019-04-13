package com.efdalincesu.nerede.model.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.efdalincesu.nerede.BR;
import com.efdalincesu.nerede.model.User;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseObservable {

    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User user;
    @Bindable
    public int getSuccess() {
        return success;
    }
    @Bindable
    public String getMessage() {
        return message;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }
    @Bindable
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return
                "LoginResponse{" +
                        "success = '" + success + '\'' +
                        ",message = '" + message + '\'' +
                        ",user = '" + user + '\'' +
                        "}";
    }
}