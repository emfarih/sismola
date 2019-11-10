package com.example.sismola.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sismola.presentation.viewmodel.DataViewModel;
import com.example.sismola.presentation.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
/**
 * @author by M on 10/11/19
 */
@Module
public abstract class ViewModelModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(DataViewModel.class)
    public abstract ViewModel dataViewModel(DataViewModel dataViewModel);
}
