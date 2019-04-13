package com.efdalincesu.nerede.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.efdalincesu.nerede.data.remote.RestApi;
import com.efdalincesu.nerede.data.remote.RestApiClient;
import com.efdalincesu.nerede.model.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private RestApi restApi;

    public MainRepository() {
        restApi = RestApiClient.getRestApi();
    }

    public LiveData<Boolean> logout(String userId, String playerId) {
        MutableLiveData<Boolean> logLiveData = new MutableLiveData<>();
        Call<BaseResponse> logCall = restApi.logout(userId, playerId);
        logCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        logLiveData.setValue(true);
                    } else {
                        logLiveData.setValue(false);
                    }
                } else {
                    logLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                logLiveData.setValue(false);
            }
        });

        return logLiveData;
    }

    public LiveData<Boolean> isLogin(String userId, String parentId) {
        MutableLiveData<Boolean> loginLiveData = new MutableLiveData<>();
        Call<BaseResponse> loginCall = restApi.isLogin(userId, parentId);
        loginCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        loginLiveData.setValue(true);
                    } else if (response.body().getSuccess() == 0) {
                        loginLiveData.setValue(false);
                    }
                } else {
                    loginLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                loginLiveData.setValue(false);
            }
        });

        return loginLiveData;
    }

}
