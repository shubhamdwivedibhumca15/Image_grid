package com.example.image_grid.retrofit;


import com.example.image_grid.model.RegistrationResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";

    //Login
    public static void getData( Callback<List<RegistrationResponse>> callback){
        RetrofitClient.getClient().getData().enqueue(callback);
    }

    //Subcatagory
   // public static void getCourses(Callback<CatagoryResponse> callback) {
       // RetrofitClient.getClient().getCourse().enqueue(callback);
    //}
}



