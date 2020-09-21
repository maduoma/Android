package com.dodemy.questionsandanswers.di.component;



import com.dodemy.questionsandanswers.di.module.DialogModule;
import com.dodemy.questionsandanswers.di.scope.DialogScope;
import com.dodemy.questionsandanswers.ui.main.rating.RateUsDialog;

import dagger.Component;



@DialogScope
@Component(modules = DialogModule.class, dependencies = AppComponent.class)
public interface DialogComponent {

    void inject(RateUsDialog dialog);

}
