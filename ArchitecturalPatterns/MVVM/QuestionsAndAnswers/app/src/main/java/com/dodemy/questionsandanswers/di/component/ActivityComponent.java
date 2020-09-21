package com.dodemy.questionsandanswers.di.component;



import com.dodemy.questionsandanswers.di.module.ActivityModule;
import com.dodemy.questionsandanswers.di.scope.ActivityScope;
import com.dodemy.questionsandanswers.ui.feed.FeedActivity;
import com.dodemy.questionsandanswers.ui.login.LoginActivity;
import com.dodemy.questionsandanswers.ui.main.MainActivity;
import com.dodemy.questionsandanswers.ui.splash.SplashActivity;

import dagger.Component;



@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(FeedActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(SplashActivity splashActivity);

}
