package com.efdalincesu.nerede.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.efdalincesu.nerede.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseMemberResponse extends BaseObservable {
    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private List<Member> user;
    @Bindable
    public int getSuccess() {
        return success;
    }
    @Bindable
    public String getMessage() {
        return message;
    }

    public void setUser(List<Member> user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }
    @Bindable
    public List<Member> getUser() {
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
