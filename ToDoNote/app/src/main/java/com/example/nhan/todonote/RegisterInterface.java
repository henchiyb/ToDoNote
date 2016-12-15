package com.example.nhan.todonote;

import com.example.nhan.todonote.models.RegisterModel;
import com.example.nhan.todonote.networks.APIUrl;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nhan on 12/14/2016.
 */

public interface RegisterInterface {
    @FormUrlEncoded
    @POST(APIUrl.API_REGISTER)
    Call<RegisterModel> registration(@Field("username") String username, @Field("password") String password);
}
