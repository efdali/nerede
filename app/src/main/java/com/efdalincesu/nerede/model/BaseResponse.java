package com.efdalincesu.nerede.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.efdalincesu.nerede.BR;
import com.google.gson.annotations.SerializedName;

public class BaseResponse extends BaseObservable {


    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    public void setSuccess(int success) {
        this.success = success;
        notifyPropertyChanged(BR.success);
    }
    @Bindable
    public int getSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }
    @Bindable
    public String getMessage() {
        return message;
    }
}
