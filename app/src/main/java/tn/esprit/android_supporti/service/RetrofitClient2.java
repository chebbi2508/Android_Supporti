package tn.esprit.android_supporti.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {
    private static RetrofitClient2 instance = null;
    private UserClient myApi;

    private RetrofitClient2() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.245:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(UserClient.class);
    }

    public static synchronized RetrofitClient2 getInstance() {
        if (instance == null) {
            instance = new RetrofitClient2();
        }
        return instance;
    }

    public UserClient getMyApi() {
        return myApi;
    }
}
