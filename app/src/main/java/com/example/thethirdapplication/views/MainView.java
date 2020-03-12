package com.example.thethirdapplication.views;

import com.example.thethirdapplication.AddToEndSingleByTagStateStrategy;
import com.example.thethirdapplication.models.Articles;
import java.util.List;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    String TAG_LOADING_COMMAND = "tagLoadingCommand";

    @StateStrategyType(OneExecutionStateStrategy.class)
    void parseData(List<Articles> body);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String message);

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_LOADING_COMMAND)
    void startLoading();

    @StateStrategyType(value = AddToEndSingleByTagStateStrategy.class, tag = TAG_LOADING_COMMAND)
    void finishLoading();

}
