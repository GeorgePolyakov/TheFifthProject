package com.example.thethirdapplication;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.thethirdapplication.NewsView;
import com.example.thethirdapplication.models.MainResponse;
import com.example.thethirdapplication.retrofit.RetrofitInstance;
import com.example.thethirdapplication.retrofit.RetrofitInterface;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> implements AdapterView.OnItemSelectedListener {
    private Call<MainResponse> listCall;
    private RetrofitInterface retrofitInterface;

    public void ShowError() {
        getViewState().showError("Ошибка");
    }

    public void Refresh() {
        getViewState().getResponse().enqueue(new Callback<MainResponse>() {
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


    @Override
    public void onNewsRecycleClick(int key) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


