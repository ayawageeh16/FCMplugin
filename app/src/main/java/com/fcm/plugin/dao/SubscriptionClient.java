package com.fcm.plugin.dao;

import com.fcm.plugin.model.ResponseModel;
import com.fcm.plugin.model.TokenModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SubscriptionClient {

   @POST("2/subscriptions")
    Call<ResponseModel> sendToken (@Header("x-pushbots-appid") String appID, @Body TokenModel token);
}
