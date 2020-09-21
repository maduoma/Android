package com.dodemy.questionsandanswers.di.module;


import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;


import com.dodemy.questionsandanswers.ViewModelProviderFactory;
import com.dodemy.questionsandanswers.data.DataManager;
import com.dodemy.questionsandanswers.ui.base.BaseActivity;
import com.dodemy.questionsandanswers.ui.feed.FeedPagerAdapter;
import com.dodemy.questionsandanswers.ui.feed.FeedViewModel;
import com.dodemy.questionsandanswers.ui.login.LoginViewModel;
import com.dodemy.questionsandanswers.ui.main.MainViewModel;
import com.dodemy.questionsandanswers.ui.splash.SplashViewModel;
import com.dodemy.questionsandanswers.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;



@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter() {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    FeedViewModel provideFeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<FeedViewModel> supplier = () -> new FeedViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<FeedViewModel> factory = new ViewModelProviderFactory<>(FeedViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(FeedViewModel.class);
    }

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SplashViewModel> supplier = () -> new SplashViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SplashViewModel> factory = new ViewModelProviderFactory<>(SplashViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SplashViewModel.class);
    }

}
