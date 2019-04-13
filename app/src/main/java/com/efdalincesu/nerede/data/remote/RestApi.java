package com.efdalincesu.nerede.data.remote;

import com.efdalincesu.nerede.model.BaseMemberResponse;
import com.efdalincesu.nerede.model.BaseResponse;
import com.efdalincesu.nerede.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @GET("isLogin.php")
    Call<BaseResponse> isLogin(@Query("userId") String userId,@Query("playerId") String playerId);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("email") String email, @Field("pass") String pass, @Field("playerId") String playerId);

    @FormUrlEncoded
    @POST("logout.php")
    Call<BaseResponse> logout(@Field("userId") String userId, @Field("playerId") String playerId);

    @FormUrlEncoded
    @POST("register.php")
    Call<BaseResponse> register(@Field("name") String name, @Field("email") String email,
                                @Field("pass") String pass, @Field("type") int type);

    @GET("searchChild.php")
    Call<BaseMemberResponse> searchChild(@Query("str") String str, @Query("userId") String userId);

    @GET("sendFriendRequest.php")
    Call<BaseResponse> sendRequest(@Query("userName") String name, @Query("parentId") String parentId,
                                   @Query("childId") String childId);

    @GET("removeFriend.php")
    Call<BaseResponse> removeFriend(@Query("parentId") String parentId, @Query("childId") String childId);

    @GET("showFriendRequest.php")
    Call<BaseMemberResponse> showFriendRequest(@Query("userId") String userId);

    @GET("acceptFriendRequest.php")
    Call<BaseResponse> acceptFriendRequest(@Query("parentId") String parentId, @Query("childId") String chilId);

    @GET("getAllFriends.php")
    Call<BaseMemberResponse> getAllFriends(@Query("userId") String userId, @Query("userType") String userType);

    @GET("sendLocationRequest.php")
    Call<BaseResponse> sendLocationRequest(@Query("userName") String name,@Query("parentId") String parentId,
                                           @Query("childId") String childId);

}
