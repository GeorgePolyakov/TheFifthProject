package com.example.thethirdapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.thethirdapplication.NewsActivity;
import com.example.thethirdapplication.NewsPresenter;
import com.example.thethirdapplication.NewsRecyclerViewAdapter;
import com.example.thethirdapplication.NewsView;
import com.example.thethirdapplication.R;
import com.example.thethirdapplication.models.Articles;
import com.example.thethirdapplication.models.MainResponse;
import com.example.thethirdapplication.retrofit.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import retrofit2.Call;

public class SecondManActivity extends MvpAppCompatActivity implements NewsView, NewsRecyclerViewAdapter.OnRecycleViewNewsListener, AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {
    @InjectPresenter
    NewsPresenter newsPresenter;

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

    @Override
    public void onRefresh() {
        newsPresenter.ShowError();
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
        // @Nothing TODO
    }


    @Override
    public void showNews() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void finishLoading() {

    }

    private void initViewSwipeToRefresh() {
        rvMain = findViewById(R.id.rvMain);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
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

    public void parseData(List<Articles> body) {
        if (!recycleFlag) {
            newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(body, this);
            rvMain.setAdapter(newsRecyclerViewAdapter);
            recycleFlag = true;
        } else {
            newsRecyclerViewAdapter.updateList(body);
        }
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


}
