package com.example.thethirdapplication;

import com.example.thethirdapplication.presenters.NewsPresenter;
import com.example.thethirdapplication.views.NewsView;
import com.example.thethirdapplication.views.NewsView$$State;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

public class NewsPresenterTest {

    @Mock
    NewsView mainView;

    @Mock
    NewsView$$State newsViewState;

    private NewsPresenter newsPresenter = new NewsPresenter();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsPresenter.attachView(mainView);
        newsPresenter.setViewState(newsViewState);
    }

    @Test
    public void testShowingData() {
        newsPresenter.setListCall(1);
        newsViewState.parseData(null);
    }

}
