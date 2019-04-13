package com.efdalincesu.nerede.data.remote;

import com.efdalincesu.nerede.util.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    public static RestApi getRestApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();


        return retrofit.create(RestApi.class);
    }

}
