package com.dodemy.questionsandanswers.ui.feed;


import com.dodemy.questionsandanswers.data.DataManager;
import com.dodemy.questionsandanswers.ui.base.BaseViewModel;
import com.dodemy.questionsandanswers.utils.rx.SchedulerProvider;

public class FeedViewModel extends BaseViewModel {

    public FeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
