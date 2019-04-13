package com.efdalincesu.nerede.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.efdalincesu.nerede.BR;
import com.google.gson.annotations.SerializedName;

public class Member extends BaseObservable {

    @SerializedName("id")
    private String userId;

    @SerializedName("mail")
    private String mail;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private int type;

    @SerializedName("state")
    private String state;

    public Member() {

    }

    public Member(String id, String name, String mail, int type,String state) {
        this.userId = id;
        this.mail = mail;
        this.name = name;
        this.type = type;
        this.state=state;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public String getUserId() {
        return userId;
    }

    public void setMail(String mail) {
        this.mail = mail;
        notifyPropertyChanged(BR.mail);
    }

    @Bindable
    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setType(int type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public int getType() {
        return type;
    }

    public void setState(String state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    @Bindable
    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "id = '" + userId + '\'' +
                        "mail = '" + mail + '\'' +
                        ",name = '" + name + '\'' +
                        ",type = '" + type + '\'' +
                        ",state = '" + state+ '\'' +
                        "}";
    }
}