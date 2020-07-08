package com.assesmenttest.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assesmenttest.models.response.Datum;
import com.assesmenttest.repositories.EmployeeDetailRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Datum>> mEmployeeData;
    private EmployeeDetailRepository mRepo;

    public void init(){
        if(mEmployeeData != null){
            return;
        }
        mRepo = EmployeeDetailRepository.getInstance();
        mEmployeeData = mRepo.getEmployeeData();
    }

    public LiveData<ArrayList<Datum>> getEmployeeData(){
        return mEmployeeData;
    }
}
