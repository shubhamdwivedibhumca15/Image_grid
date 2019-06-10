package com.example.image_grid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.image_grid.model.RegistrationResponse;
import com.example.image_grid.retrofit.RestClient;
import com.example.image_grid.util.AppUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);


        loadImages();


    }




    private void loadImages() {

        if (AppUtils.isInternetConnected(this)) {
            AppUtils.showProgressDialog(this, "Please wait...");


            RestClient.getData(new Callback<List<RegistrationResponse>>() {

                @Override
                public void onResponse(Call<List<RegistrationResponse>> call, Response<List<RegistrationResponse>> response) {
                    AppUtils.dismisDialog();
                    List<RegistrationResponse> registrationResponse = response.body();
                    if (registrationResponse != null && registrationResponse.size()>0) {


                        ImageListAdapter imageListAdapter = new ImageListAdapter(getApplicationContext());
                        imageListAdapter.setData(registrationResponse);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        recyclerView.setAdapter(imageListAdapter);

                        System.out.println("Data from server:"+registrationResponse.size());
                    } else {
                        System.out.println("Data Not Recived");
                    }
                }

                @Override
                public void onFailure(Call<List<RegistrationResponse>> call, Throwable t) {
                    AppUtils.dismisDialog();

                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }
}
