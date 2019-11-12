package com.fcm.plugin.repository;

import android.util.Log;

import com.fcm.plugin.dao.RetrofitClient;
import com.fcm.plugin.dao.SubscriptionClient;
import com.fcm.plugin.model.ResponseModel;
import com.fcm.plugin.model.TokenModel;
import com.google.gson.Gson;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionRepository {

    public static final String REQUEST_RESPONSE = "REQUEST_RESPONSE";
    public static final String REQUEST_FAILURE = "REQUEST_FAILURE";

    private static ResponseModel responseModel = new ResponseModel();

    public static ResponseModel sendToken(String appId, TokenModel token) {

        SubscriptionClient client = RetrofitClient.getInstance().create(SubscriptionClient.class);
        Call<ResponseModel> call = client.sendToken(appId, token);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.i(REQUEST_RESPONSE, response.code() + ", Body: " + new Gson().toJson(response.body()));

                if (response != null && response.code() == 200) {
                    responseModel = response.body();
                } else {
                    responseModel = null;
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.i(REQUEST_FAILURE, t.getMessage());
                responseModel = null;
            }
        });
        return responseModel;
    }

}
