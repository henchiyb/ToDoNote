package com.example.nhan.todonote.networks;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nhan on 12/14/2016.
 */

public class ServiceFactory {
    private Retrofit retrofit;
    public static ServiceFactory instance = new ServiceFactory();

    public ServiceFactory(){
        retrofit = new Retrofit.Builder().baseUrl("http://a-server.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <ServiceClass>ServiceClass createService(Class<ServiceClass> serviceClass){
        return retrofit.create(serviceClass);
    }

    public <ServiceClass>ServiceClass createService(Class<ServiceClass> serviceClass, Context context){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString("token", null);
        if (token != null) {
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("token", token)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        return retrofit.create(serviceClass);
    }
}
