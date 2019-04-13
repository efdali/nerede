package com.efdalincesu.nerede.ui.main.request;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.efdalincesu.nerede.data.remote.RestApi;
import com.efdalincesu.nerede.data.remote.RestApiClient;
import com.efdalincesu.nerede.model.BaseMemberResponse;
import com.efdalincesu.nerede.model.BaseResponse;
import com.efdalincesu.nerede.model.Member;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRepository {

    private RestApi restApi;

    public RequestRepository(){
        restApi= RestApiClient.getRestApi();
    }

    public MutableLiveData<List<Member>> getFriendRequest(String userId){
        MutableLiveData<List<Member>> searchLiveData=new MutableLiveData<>();
        Call<BaseMemberResponse> searchCall=restApi.showFriendRequest(userId);
        searchCall.enqueue(new Callback<BaseMemberResponse>() {
            @Override
            public void onResponse(Call<BaseMemberResponse> call, Response<BaseMemberResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess()==1){
                        List<Member> tempList=response.body().getUser()!=null ?response.body().getUser(): new ArrayList<>();
                        searchLiveData.setValue(tempList);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseMemberResponse> call, Throwable t) {

            }
        });


        return searchLiveData;
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

    public LiveData<Boolean> acceptFriend(String parentId, String childId) {
        MutableLiveData<Boolean> friendLiveData = new MutableLiveData<>();

        Call<BaseResponse> friendCall = restApi.acceptFriendRequest(parentId, childId);
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
