package com.example.thethirdapplication.views;

import com.example.thethirdapplication.models.Articles;
import com.example.thethirdapplication.models.MainResponse;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import retrofit2.Response;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewsView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void parseData(Response<MainResponse> response);


}
