package com.assesmenttest.network;

import com.assesmenttest.constant.AppUrls;
import com.assesmenttest.models.response.Datum;
import com.assesmenttest.models.response.EmployeeDetailModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {

    @GET(AppUrls.API_EMPLOYEE_DATA)
    Call<EmployeeDetailModel> getEmployeeList();
}
