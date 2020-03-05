package com.example.thethirdapplication;

import com.example.thethirdapplication.models.*;
import com.example.thethirdapplication.retrofit.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, NewsRecyclerViewAdapter.OnRecycleViewNewsListener {

    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private RecyclerView rvMain;
    private Call<MainResponse> listCall;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RetrofitInterface retrofitInterface;
    private View mErrorView;
    private Spinner spinnerTheme;
    private List<String> themesNewsList;
    private int keyTheme = 0;
    private boolean recycleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mErrorView = findViewById(R.id.error_view);
            initViewSwipeToRefresh();
            fillSpinnerAdapter();
            spinnerTheme.setOnItemSelectedListener(this);
            onRefresh();
    }

    private void parseData(List<Articles> body) {
        if (!recycleFlag) {
            newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(body, this);
            rvMain.setAdapter(newsRecyclerViewAdapter);
            recycleFlag = true;
        } else {
            newsRecyclerViewAdapter.updateList(body);
        }
    }

    private void initViewSwipeToRefresh() {
        rvMain = findViewById(R.id.rvMain);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        showHideError(true);
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
                parseData(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.i("myTag", t + "");
                showHideError(false);
            }

        });
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void fillSpinnerAdapter() {
        spinnerTheme = findViewById(R.id.spinnerTheme);
        themesNewsList = new ArrayList<String>();
        themesNewsList.add("Software");
        themesNewsList.add("Bitcoin");
        themesNewsList.add("Business of USA");
        themesNewsList.add("Apple");
        themesNewsList.add("TechCrunch");
        themesNewsList.add("Wall Street Journal");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, themesNewsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheme.setAdapter(adapter);
    }

    private void showHideError(boolean state) {
        if (state) {
            mErrorView.setVisibility(View.GONE);
            rvMain.setVisibility(View.VISIBLE);
            spinnerTheme.setVisibility(View.VISIBLE);
        } else {
            rvMain.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
            spinnerTheme.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNewsRecycleClick(int key) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("keyTheme", keyTheme);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        keyTheme = position;
        onRefresh();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Nothing TODO
    }
}
