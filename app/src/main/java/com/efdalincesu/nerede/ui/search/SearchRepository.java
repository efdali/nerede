package com.efdalincesu.nerede.ui.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.efdalincesu.nerede.data.remote.RestApi;
import com.efdalincesu.nerede.data.remote.RestApiClient;
import com.efdalincesu.nerede.model.BaseResponse;
import com.efdalincesu.nerede.model.Member;
import com.efdalincesu.nerede.model.BaseMemberResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {

    private RestApi restApi;

    public SearchRepository() {
        restApi = RestApiClient.getRestApi();
    }


    public LiveData<List<Member>> search(String str, String userId) {

        MutableLiveData<List<Member>> userLiveData = new MutableLiveData<>();
        Call<BaseMemberResponse> searchCall = restApi.searchChild(str, userId);
        searchCall.enqueue(new Callback<BaseMemberResponse>() {
            @Override
            public void onResponse(Call<BaseMemberResponse> call, Response<BaseMemberResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        List<Member> tempList=response.body().getUser()!=null ?response.body().getUser(): new ArrayList<>();
                        userLiveData.setValue(tempList);
                    } else if (response.body().getSuccess() == 0) {

                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMemberResponse> call, Throwable t) {

            }
        });
        return userLiveData;
    }

    public MutableLiveData<Boolean> sendRequest(String name, String parentId, String chilId) {

        MutableLiveData<Boolean> requestLiveData = new MutableLiveData<>();
        Call<BaseResponse> requestCall = restApi.sendRequest(name, parentId, chilId);
        requestCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        requestLiveData.setValue(true);
                    } else {
                        requestLiveData.setValue(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                requestLiveData.setValue(true);
            }
        });

        return requestLiveData;
    }

    public LiveData<Boolean> removeFriend(String parentId, String childId) {
        MutableLiveData<Boolean> friendLiveData = new MutableLiveData<>();

        Call<BaseResponse> friendCall = restApi.removeFriend(parentId, childId);
        friendCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        friendLiveData.setValue(true);
                    } else {
                        friendLiveData.setValue(false);
                    }
                } else {
                    friendLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                friendLiveData.setValue(false);

            }
        });

        return friendLiveData;
    }

}
