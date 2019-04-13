package com.efdalincesu.nerede.ui.main.friend;

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

public class FriendRepository {

    private RestApi restApi;

    public FriendRepository() {
        restApi = RestApiClient.getRestApi();
    }

    public LiveData<List<Member>> getAllFriends(String userId, String userType) {

        MutableLiveData<List<Member>> memberLiveData = new MutableLiveData<>();

        Call<BaseMemberResponse> friendsCall = restApi.getAllFriends(userId, userType);
        friendsCall.enqueue(new Callback<BaseMemberResponse>() {
            @Override
            public void onResponse(Call<BaseMemberResponse> call, Response<BaseMemberResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        List<Member> tempList = response.body().getUser() != null ? response.body().getUser() : new ArrayList<>();
                        memberLiveData.setValue(tempList);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMemberResponse> call, Throwable t) {

            }
        });


        return memberLiveData;
    }

    public LiveData<Boolean> sendLocationRequest(String name, String parentId, String childId) {
        MutableLiveData<Boolean> locationLiveData = new MutableLiveData<>();
        Call<BaseResponse> locationCall = restApi.sendLocationRequest(name, parentId, childId);
        locationCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1)
                        locationLiveData.setValue(true);
                    else
                        locationLiveData.setValue(false);
                } else
                    locationLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                locationLiveData.setValue(false);
            }
        });

        return locationLiveData;
    }

    public LiveData<Boolean> removeFriend(String parentId, String childId) {
        MutableLiveData<Boolean> friendLiveData = new MutableLiveData<>();
        Call<BaseResponse> friendCall = restApi.removeFriend(parentId, childId);
        friendCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1)
                        friendLiveData.setValue(true);
                    else
                        friendLiveData.setValue(false);
                }else
                    friendLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                friendLiveData.setValue(false);
            }
        });

        return friendLiveData;
    }

}
