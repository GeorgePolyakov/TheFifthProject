package com.example.thethirdapplication;

import com.example.thethirdapplication.models.Articles;
import com.example.thethirdapplication.models.MainResponse;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import retrofit2.Response;

@StateStrategyType(AddToEndSingleStrategy.class)
interface NewsView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void parseData(Response<MainResponse> response);


}
