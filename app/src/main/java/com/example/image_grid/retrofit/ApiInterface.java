package com.example.image_grid.retrofit;


import com.example.image_grid.model.RegistrationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //login
    @GET("json/glide.json")
    Call <List<RegistrationResponse>> getData();



   // SubCatagory
   // @Multipart
   // @POST("cat_api/test_api.php?action=fetchsub_all")
    //Call<SubcategoryResponse>getCourse();

}
