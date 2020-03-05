package com.example.thethirdapplication.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {

    private String status;
    private List <Articles> articles;
    private String totalResults;

    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List <Articles> getArticles() {
        return articles;
    }

}
