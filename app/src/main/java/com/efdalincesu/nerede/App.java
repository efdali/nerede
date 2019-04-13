package com.efdalincesu.nerede;

import android.app.Application;
import android.widget.Toast;

import com.efdalincesu.nerede.data.remote.RestApi;
import com.efdalincesu.nerede.data.remote.RestApiClient;
import com.efdalincesu.nerede.model.BaseResponse;
import com.efdalincesu.nerede.util.Constants;
import com.efdalincesu.nerede.util.SessionManager;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App extends Application {

    SessionManager session;
    RestApi restApi;

    @Override
    public void onCreate() {
        super.onCreate();

        session = new SessionManager(getApplicationContext());
        restApi = RestApiClient.getRestApi();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationOpenHandler())
                .init();

    }

    private class NotificationOpenHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            String buttonId = result.action.actionID;
            JSONObject data = result.notification.payload.additionalData;
            String datavalue=null;


            if (data != null) {
                datavalue = data.optString(Constants.NOTIF_PARENT_ID, null);
            }

            if (buttonId.equals(Constants.BTN_ACCEPT)) {
                accept(datavalue,session.getUserId());
            } else if (buttonId.equals(Constants.BTN_REJECT)) {
                reject(datavalue,session.getUserId());
            }else if(buttonId.equals(Constants.LOCATION_ACCEPT)){


            }else if(buttonId.equals(Constants.LOCATION_REJECT)){

            }

        }
    }

    public void accept(String parentId, String childId) {
        restApi.acceptFriendRequest(parentId,childId).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(getApplicationContext(),"Onaylandı",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void reject(String parentId,String childId){
        restApi.removeFriend(parentId,childId).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(getApplicationContext(),"Reddedildi.",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Hata.",Toast.LENGTH_LONG).show();

            }
        });
    }
}
