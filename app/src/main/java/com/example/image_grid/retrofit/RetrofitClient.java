package com.example.image_grid.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static ApiInterface sRetrofitClient;



    public static ApiInterface getClient() {
        synchronized (RetrofitClient.class) {
            if (sRetrofitClient == null) {
                sRetrofitClient = getRetrofit().create(ApiInterface.class);
            }
        }
        return sRetrofitClient;
    }


    private static OkHttpClient okHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);


        return builder.build();
    }

    public static Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttp());
        builder.baseUrl("https://api.androidhive.info/");
        builder.addConverterFactory(GsonConverterFactory.create(gson()));

        return builder.build();
    }
    public static Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-M  M-dd'T'HH:mm:ssZ").create();
    }

}