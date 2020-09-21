package com.dodemy.questionsandanswers.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;


import com.dodemy.questionsandanswers.ViewModelProviderFactory;
import com.dodemy.questionsandanswers.data.DataManager;
import com.dodemy.questionsandanswers.ui.base.BaseDialog;
import com.dodemy.questionsandanswers.ui.main.rating.RateUsViewModel;
import com.dodemy.questionsandanswers.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;


@Module
public class DialogModule {

    private BaseDialog dialog;

    public DialogModule(BaseDialog dialog){
        this.dialog = dialog;
    }

    @Provides
    RateUsViewModel provideRateUsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<RateUsViewModel> supplier = () -> new RateUsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<RateUsViewModel> factory = new ViewModelProviderFactory<>(RateUsViewModel.class, supplier);
        return new ViewModelProvider(dialog.getActivity(), factory).get(RateUsViewModel.class);
    }

}
