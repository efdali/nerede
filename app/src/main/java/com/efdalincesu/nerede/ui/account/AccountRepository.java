package com.efdalincesu.nerede.ui.account;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.efdalincesu.nerede.data.remote.RestApi;
import com.efdalincesu.nerede.data.remote.RestApiClient;
import com.efdalincesu.nerede.model.BaseResponse;
import com.efdalincesu.nerede.model.User;
import com.efdalincesu.nerede.model.login.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    RestApi restApi;
    Application application;
    public AccountRepository(Application application){
        restApi= RestApiClient.getRestApi();
        this.application=application;
    }


    public LiveData<User> login(String email, String pass,String playerId){
        MutableLiveData<User> responseLiveData=new MutableLiveData<>();
        Call<LoginResponse> loginCall=restApi.login(email,pass,playerId);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess()==1){
                        responseLiveData.setValue(response.body().getUser());
                    }else if (response.body().getSuccess()==0){
                        Toast.makeText(application.getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


        return responseLiveData;
    }

    public LiveData<Boolean> register(String name,String email,String pass,int type){

        MutableLiveData<Boolean> accountLiveData=new MutableLiveData<>();

        Call<BaseResponse> registerCall=restApi.register(name, email, pass, type);
        registerCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess()==1){
                        accountLiveData.setValue(true);
                    }else if (response.body().getSuccess()==0){
                        accountLiveData.setValue(false);
                        Toast.makeText(application.getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });


        return accountLiveData;
    }

}
