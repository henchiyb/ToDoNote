package com.example.nhan.todonote.networks;

import com.example.nhan.todonote.models.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nhan on 12/14/2016.
 */

public interface LoginInterface {
    @FormUrlEncoded
    @POST(APIUrl.API_LOGIN)
    Call<LoginModel> login(@Field("username") String username, @Field("password") String password);
}
