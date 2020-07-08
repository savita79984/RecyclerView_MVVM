package com.assesmenttest.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.assesmenttest.constant.AppUrls;
import com.assesmenttest.models.response.EmployeeDetailModel;
import com.assesmenttest.network.NetworkInterface;
import com.assesmenttest.models.response.Datum;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton pattern
 */
public class EmployeeDetailRepository {

    private NetworkInterface networkInterface;
    private static EmployeeDetailRepository instance;
    private MutableLiveData<ArrayList<Datum>> data = new MutableLiveData<>();

    public EmployeeDetailRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        networkInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(AppUrls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkInterface.class);
    }

    public static EmployeeDetailRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeDetailRepository();
        }
        return instance;
    }

    public void getEmployeeList() {
        networkInterface.getEmployeeList()
                .enqueue(new Callback<EmployeeDetailModel>() {
                    @Override
                    public void onResponse(@NotNull Call<EmployeeDetailModel> call, @NotNull Response<EmployeeDetailModel> response) {
                        if (response.body() != null) {
                            Log.d("response", "" + response.body());
                            data.postValue(response.body().getData());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<EmployeeDetailModel> call, @NotNull Throwable t) {
                        Log.d("fail", "---");
                        t.printStackTrace();
                        data.postValue(null);
                    }
                });
    }

    public MutableLiveData<ArrayList<Datum>> getEmployeeData() {
        getEmployeeList();
        return data;
    }
}











