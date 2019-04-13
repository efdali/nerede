package com.efdalincesu.nerede.ui.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.efdalincesu.nerede.model.Member;

import java.util.List;

public class SearchViewModel extends ViewModel {

    SearchRepository repository;

    public SearchViewModel(){
        repository=new SearchRepository();
    }

    public LiveData<List<Member>> search(String str, String userId){
        return repository.search(str,userId);
    }

    public LiveData<Boolean> sendRequest(String name,String parentId,String childId){
        return repository.sendRequest(name, parentId, childId);
    }

    public LiveData<Boolean> removeFriend(String parentId,String childId){
        return repository.removeFriend(parentId, childId);
    }

}
