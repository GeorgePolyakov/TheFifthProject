package com.example.thethirdapplication.activities;

import com.example.thethirdapplication.presenters.MainPresenter;
import com.example.thethirdapplication.NewsRecyclerViewAdapter;
import com.example.thethirdapplication.R;
import com.example.thethirdapplication.models.*;
import com.example.thethirdapplication.retrofit.*;
import com.example.thethirdapplication.views.MainView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import retrofit2.Call;

public class MainActivity extends MvpAppCompatActivity implements MainView, AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, NewsRecyclerViewAdapter.OnRecycleViewNewsListener {

    @InjectPresenter
    MainPresenter mainPresenter;

    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private RecyclerView rvMain;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Spinner spinnerTheme;
    private List<String> themesNewsList;
    private int keyTheme = 0;
    private boolean recycleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewSwipeToRefresh();
        fillSpinnerAdapter();
        spinnerTheme.setOnItemSelectedListener(this);
         onRefresh();
    }

    @Override
    public void parseData(List<Articles> body) {
        if (!recycleFlag) {
            newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(body, this);
            rvMain.setAdapter(newsRecyclerViewAdapter);
            recycleFlag = true;
        } else {
            newsRecyclerViewAdapter.updateList(body);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        keyTheme = position;
        onRefresh();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // @Nothing TODO
    }

    @Override
    public void onNewsRecycleClick(int key) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("keyTheme", keyTheme);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoading() {
        mainPresenter.Refresh(keyTheme);
    }

    @Override
    public void finishLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
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
        startLoading();
        finishLoading();
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
}
