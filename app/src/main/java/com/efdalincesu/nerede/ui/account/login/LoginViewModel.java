package com.efdalincesu.nerede.ui.account.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.efdalincesu.nerede.model.User;
import com.efdalincesu.nerede.ui.account.AccountRepository;

public class LoginViewModel extends AndroidViewModel {

    AccountRepository repository;

    public LoginViewModel(Application application) {
        super(application);
        repository = new AccountRepository(application);
    }

    public LiveData<User> login(String email, String pass,String playerId) {
        return repository.login(email, pass,playerId);
    }

}
