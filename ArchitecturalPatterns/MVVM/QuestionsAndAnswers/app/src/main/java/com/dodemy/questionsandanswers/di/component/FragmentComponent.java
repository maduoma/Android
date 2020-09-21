package com.dodemy.questionsandanswers.di.component;



import com.dodemy.questionsandanswers.di.module.FragmentModule;
import com.dodemy.questionsandanswers.di.scope.FragmentScope;
import com.dodemy.questionsandanswers.ui.about.AboutFragment;
import com.dodemy.questionsandanswers.ui.feed.blogs.BlogFragment;
import com.dodemy.questionsandanswers.ui.feed.opensource.OpenSourceFragment;

import dagger.Component;



@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(BlogFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(AboutFragment fragment);
}
