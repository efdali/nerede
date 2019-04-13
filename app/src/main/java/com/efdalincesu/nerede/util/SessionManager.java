package com.efdalincesu.nerede.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.efdalincesu.nerede.model.User;

public class SessionManager {


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(Constants.SHARED_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public void destroySession() {

        editor.clear();
        editor.commit();
    }

    public void createSession(User user) {
        editor.putBoolean(Constants.SHARED_IS_LOGIN, true);
        editor.putString(Constants.SHARED_USER_NAME, user.getName());
        editor.putString(Constants.SHARED_USER_ID, user.getUserId());
        editor.putString(Constants.SHARED_USER_EMAIL, user.getMail());
        editor.putInt(Constants.SHARED_USER_TYPE, user.getType());

        editor.commit();
    }

    public User getUser() {
        User user = new User(getUserId(),getName(), getEmail(), getType());

        return user;
    }

    public String getUserId() {
        return preferences.getString(Constants.SHARED_USER_ID, "-1");
    }

    public String getName() {
        return preferences.getString(Constants.SHARED_USER_NAME, "-1");
    }

    public String getEmail() {
        return preferences.getString(Constants.SHARED_USER_EMAIL, "-1");
    }

    public int getType() {
        return preferences.getInt(Constants.SHARED_USER_TYPE, -1);
    }

    public boolean isLogin() {
        return preferences.getBoolean(Constants.SHARED_IS_LOGIN, false);
    }
}
