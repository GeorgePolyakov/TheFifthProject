package com.example.thethirdapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thethirdapplication.models.Articles;
import com.example.thethirdapplication.models.MainResponse;
import com.example.thethirdapplication.retrofit.RetrofitInstance;
import com.example.thethirdapplication.retrofit.RetrofitInterface;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsActivity extends MvpAppCompatActivity implements NewsView{

    @InjectPresenter
    NewsPresenter newsPresenter;

    private int position;
    private int keyTheme;
    private RetrofitInterface retrofitInterface;
    private Call<MainResponse> listCall;
    private TextView titleTextView;
    private TextView sourceTextView;
    private TextView descriptionTextView;
    private ImageView glideImageView;
    private ImageView picassoImageView;
    private Response<MainResponse> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initValues();
        newsPresenter.setListCall(keyTheme);
    }

    @Override
    public void parseData(Response<MainResponse> response) {

        titleTextView.setText(response.body().getArticles().get(position).getTitle() + "");
        sourceTextView.setText(response.body().getArticles().get(position).getSource().getName() + "");
        descriptionTextView.setText(response.body().getArticles().get(position).getDescription() + "");
        callGlide(response);
        callPicasso(response);
    }

    public void initValues() {
        titleTextView = findViewById(R.id.titleTextView);
        sourceTextView = findViewById(R.id.sourceTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        glideImageView = findViewById(R.id.glideImageView);
        picassoImageView = findViewById(R.id.picassoImageView);
        position = getIntent().getIntExtra("key", 0);
        keyTheme = getIntent().getIntExtra("keyTheme", 0);
        retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
    }

    public void callGlide(Response<MainResponse> response) {
        Glide.with(glideImageView.getContext())
                .load(response.body().getArticles().get(position).getUrlToImage())
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(glideImageView);
    }

    public void callPicasso(Response<MainResponse> response) {
        Picasso.with(picassoImageView.getContext())
                .load(response.body().getArticles().get(position).getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(picassoImageView);
    }
}
