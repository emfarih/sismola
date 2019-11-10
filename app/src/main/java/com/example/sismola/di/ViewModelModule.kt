package com.example.sismola.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sismola.presentation.viewmodel.DataViewModel
import com.example.sismola.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
/**
 * @author by M on 10/11/19
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DataViewModel::class)
    abstract fun dataViewModel(dataViewModel: DataViewModel): ViewModel
}