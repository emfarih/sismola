package com.example.sismola.di;

import com.example.sismola.presentation.activity.MainActivity;
import dagger.Component;
/**
 * @author by M on 10/11/19
 */
@Component(modules = {ApiModule.class, ViewModelModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
