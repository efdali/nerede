package com.efdalincesu.nerede.ui.account.register;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.efdalincesu.nerede.ui.account.AccountRepository;

public class RegisterViewModel extends AndroidViewModel {

    AccountRepository repository;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository=new AccountRepository(application);
    }

    public LiveData<Boolean> register(String name , String email, String pass, int type){
        return repository.register(name, email, pass, type);
    }

}
