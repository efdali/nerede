package com.efdalincesu.nerede.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

public class MainViewModel extends ViewModel {

    private MainRepository repository;

    public MainViewModel(){
        repository=new MainRepository();
    }

    public LiveData<Boolean> logout(String userId,String playerId){
        return repository.logout(userId, playerId);
    }

    public LiveData<Boolean> isLogin(String userId,String parentId){
        return repository.isLogin(userId, parentId);
    }

}
