package com.efdalincesu.nerede.ui.main.friend;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.efdalincesu.nerede.model.Member;

import java.util.List;

public class FriendViewModel extends ViewModel {

    FriendRepository repository;

    public FriendViewModel(){
        repository=new FriendRepository();
    }

    public LiveData<List<Member>> getFriends(String userId,String userType){
        return repository.getAllFriends(userId,userType);
    }

    public LiveData<Boolean> sendLocationRequest(String name,String parentId,String childId){
        return repository.sendLocationRequest(name, parentId, childId);
    }

    public LiveData<Boolean> removeFriend(String parentId,String childId){
        return repository.removeFriend(parentId, childId);
    }

}
