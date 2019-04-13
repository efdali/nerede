package com.efdalincesu.nerede.ui.account.register;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.efdalincesu.nerede.R;
import com.efdalincesu.nerede.databinding.ActivityRegisterBinding;
import com.efdalincesu.nerede.ui.account.login.LoginActivity;
import com.efdalincesu.nerede.util.Constants;
import com.efdalincesu.nerede.util.Snack;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    RegisterViewModel viewModel;

    String name, email, pass, playerId;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        binding.info.setOnClickListener(view -> {
            createInfoAlert();
        });


    }

    public void goLoginActivity(View v) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void register(View view) {

        if (validate()) {


            OSPermissionSubscriptionState state = OneSignal.getPermissionSubscriptionState();
            playerId = state.getSubscriptionStatus().getUserId();

            viewModel.register(name, email, pass, type).observe(this, response -> {

                if (response) {
                    Toast.makeText(getBaseContext(), getString(R.string.register_ok), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra(Constants.EXTRA_EMAIL, email);
                    startActivity(intent);
                    finish();
                }

            });

        }


    }

    public void createInfoAlert() {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.type_info))
                .setCancelable(true)
                .setTitle(getString(R.string.type_title))
                .setIcon(R.drawable.where_icon)
                .setPositiveButton(getString(R.string.okey), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        alertDialog.show();
    }

    public boolean validate() {

        boolean isValidate = true;
        View focusView = null;

        name = binding.inputName.getText().toString().trim();
        email = binding.inputEmail.getText().toString().trim();
        pass = binding.inputPassword.getText().toString().trim();

        int selected = binding.typeGroup.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selected);
        String rbText = rb.getText().toString();
        if (rbText.equals(getString(R.string.type_parent))) {
            type = 0;
        } else {
            type = 1;
        }

        if (name.isEmpty()) {
            isValidate = false;
            focusView = binding.inputName;
            binding.inputName.setError(getString(R.string.et_error));
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValidate = false;
            focusView = binding.inputEmail;
            binding.inputEmail.setError(getString(R.string.email_format));
        } else if (pass.isEmpty() || pass.length() < 6) {
            isValidate = false;
            focusView = binding.inputPassword;
            binding.inputPassword.setError(getString(R.string.pass_format));
        }

        if (focusView != null)
            focusView.requestFocus();

        return isValidate;
    }
}
