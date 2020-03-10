package com.example.thethirdapplication;

import com.example.thethirdapplication.AddToEndSingleByTagStateStrategy;
import com.example.thethirdapplication.models.Articles;
import com.example.thethirdapplication.models.MainResponse;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import retrofit2.Call;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewsView  extends MvpView {

    String TAG_LOADING_COMMAND = "tagLoadingCommand";
    void  showNews();

    void parseData(List<Articles> body);

    Call<MainResponse> getResponse();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String message);

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_LOADING_COMMAND)
    void startLoading();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_LOADING_COMMAND)
    void finishLoading();

}
