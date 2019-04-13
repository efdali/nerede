package com.efdalincesu.nerede.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.efdalincesu.nerede.BR;
import com.google.gson.annotations.SerializedName;
import com.onesignal.OneSignal;

public class User extends BaseObservable {

    @SerializedName("id")
    private String userId;

    @SerializedName("mail")
    private String mail;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private int type;

    @SerializedName("playerId")
    private String playerId;

    public User() {

    }

    public User(String id, String name, String mail, int type) {
        this.userId = id;
        this.mail = mail;
        this.name = name;
        this.type = type;
        this.playerId = getPlayerId();
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

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
        notifyPropertyChanged(BR.playerId);
    }

    @Bindable
    public String getPlayerId() {
        if (playerId == null) {
            playerId = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
        }
        return playerId;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "id = '" + userId + '\'' +
                        "mail = '" + mail + '\'' +
                        ",name = '" + name + '\'' +
                        ",type = '" + type + '\'' +
                        ",playerId = '" + playerId + '\'' +
                        "}";
    }
}