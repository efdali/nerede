package com.efdalincesu.nerede.ui.main.request;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.efdalincesu.nerede.model.Member;
import com.efdalincesu.nerede.ui.main.location.LocationRepository;

import java.util.List;

public class RequestViewModel extends ViewModel {

    private RequestRepository repository;

    public RequestViewModel(){
        repository=new RequestRepository();
    }

    public LiveData<List<Member>> getFriendRequest(String userId){
        return repository.getFriendRequest(userId);
    }

    public LiveData<Boolean> acceptFriendRequest(String parentId,String childId){
        return repository.acceptFriend(parentId, childId);
    }

    public LiveData<Boolean> rejectFriendRequest(String parentId,String childId){
        return repository.removeFriend(parentId,childId);
    }
}
