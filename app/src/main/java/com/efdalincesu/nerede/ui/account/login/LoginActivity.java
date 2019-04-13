package com.efdalincesu.nerede.ui.account.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.databinding.ActivityLoginBinding;
import com.efdalincesu.nerede.ui.account.register.RegisterActivity;
import com.efdalincesu.nerede.ui.main.MainActivity;
import com.efdalincesu.nerede.util.Constants;
import com.efdalincesu.nerede.util.SessionManager;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel viewModel;
    String email, pass;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sessionManager = new SessionManager(this);
        if (sessionManager.isLogin())
            goMainActivity();
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        String email=getIntent().getStringExtra(Constants.EXTRA_EMAIL);
        if (email!=null){
            binding.inputEmail.setText(email);
        }


    }

    public void goRegisterActivity(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public boolean validate() {

        boolean state = true;
        View focusView = null;
        String error = getResources().getString(R.string.et_error);
        email = binding.inputEmail.getText().toString().trim();
        pass = binding.inputPassword.getText().toString().trim();

        if (email.isEmpty()) {
            binding.inputEmail.setError(error);
            focusView = binding.inputEmail;
            state = false;
        } else
            binding.inputEmail.setError(null);

        if (pass.isEmpty()) {
            binding.inputPassword.setError(error);
            focusView = binding.inputPassword;
            state = false;
        } else
            binding.inputPassword.setError(null);

        if (focusView != null) {
            focusView.requestFocus();
        }

        return state;
    }

    public void login(View view) {


        if (validate()) {

            viewModel.login(email, pass, sessionManager.getUser().getPlayerId()).observe(this, response -> {
                sessionManager.createSession(response);
                goMainActivity();
            });
        }

    }

    public void goMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
