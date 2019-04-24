package com.exequieltiglao.retrofitciftut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvResult;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List<Photos>> call = apiInterface.getPhotos();

        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {

                if (!response.isSuccessful()) {
                    tvResult.setText("Code: " + response.code());
                    Log.d(TAG, "response.... " + response.code());
                    return;
                }

                List<Photos> posts = response.body();

                for (Photos posts1 : posts) {
                    String content = "";
                    content += "AlbumId: " + posts1.getAlbumId() + "\n";
                    content += "Id: " + posts1.getId() + "\n";
                    content += "Title: " + posts1.getTitle() + "\n";
                    content += "Url: " + posts1.getUrl() + "\n";
                    content += "ThumbnailUrl: " + posts1.getThumbnailUrl() + "\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                tvResult.setText(t.getMessage());
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
