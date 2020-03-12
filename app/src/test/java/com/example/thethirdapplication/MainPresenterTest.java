package com.example.thethirdapplication;

import com.example.thethirdapplication.presenters.MainPresenter;
import com.example.thethirdapplication.views.MainView;
import com.example.thethirdapplication.views.MainView$$State;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

public class MainPresenterTest {

    @Mock
    MainView mainView;

    @Mock
    MainView$$State mainViewState;

    private MainPresenter presenter = new MainPresenter();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(mainView);
        presenter.setViewState(mainViewState);
    }

    @Test
    public void testStartLoading() {
        String str = "Error";
        presenter.Refresh(1);
        presenter.ShowError(str);
        Mockito.verify(mainViewState).showError(str);
    }
}
