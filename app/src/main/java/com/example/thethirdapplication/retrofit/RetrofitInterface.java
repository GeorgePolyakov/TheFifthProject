package com.example.thethirdapplication.retrofit;


import com.example.thethirdapplication.models.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/v2/everything?q=bitcoin&sortBy=publishedAt")
    Call<MainResponse> getAllBitcoinNews(@Query("from") String currentDate, @Query("apiKey") String apiKey);

    @GET("/v2/top-headlines?country=us&category=business")
    Call<MainResponse> businessOfUsa(@Query("apiKey") String apiKey);

    @GET("/v2/everything?q=apple&from=2020-02-18&to=2020-02-18&sortBy=popularity")
    Call<MainResponse> getAllAppleNews(@Query("apiKey") String apiKey);

    @GET("/v2/top-headlines?sources=techcrunch")
    Call<MainResponse> techCrunch(@Query("apiKey") String apiKey);

    @GET("/v2/everything?domains=wsj.com")
    Call<MainResponse> wallStreetJournal(@Query("apiKey") String apiKey);

    @GET("/v2/everything?q=software&sortBy=publishedAt")
    Call<MainResponse> getAllSoftwareNews(@Query("from") String from, @Query("apiKey") String apiKey);
}
