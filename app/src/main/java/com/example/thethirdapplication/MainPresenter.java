package com.example.thethirdapplication;

import android.util.Log;

import com.example.thethirdapplication.models.MainResponse;
import com.example.thethirdapplication.retrofit.RetrofitInstance;
import com.example.thethirdapplication.retrofit.RetrofitInterface;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>  {
    private Call<MainResponse> listCall;
    private RetrofitInterface retrofitInterface;
    private int keyTheme;


    public void ShowError() {
        getViewState().showError("Ошибка");
    }

    public void Refresh(int keyTheme) {

        retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        switch (keyTheme) {
            case 0:
                listCall = retrofitInterface.getAllSoftwareNews(NewsUtility.getSpecificDate(), NewsUtility.apiKey);
                break;
            case 1:
                listCall = retrofitInterface.getAllBitcoinNews(NewsUtility.getSpecificDate(), NewsUtility.apiKey);
                break;
            case 2:
                listCall = retrofitInterface.businessOfUsa(NewsUtility.apiKey);
                break;
            case 3:
                listCall = retrofitInterface.getAllAppleNews(NewsUtility.apiKey);
                break;
            case 4:
                listCall = retrofitInterface.techCrunch(NewsUtility.apiKey);
                break;
            case 5:
                listCall = retrofitInterface.wallStreetJournal(NewsUtility.apiKey);
                break;
            default:
                listCall = retrofitInterface.getAllSoftwareNews(NewsUtility.getSpecificDate(), NewsUtility.apiKey);
                break;
        }

        listCall.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.i("myTag", response.raw() + "");
                getViewState().parseData(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.i("myTag", t + "");
                ShowError();
            }

        });
    }

}


