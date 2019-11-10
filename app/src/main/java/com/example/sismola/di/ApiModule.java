package com.example.sismola.di;

import com.example.sismola.data.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author by M on 10/11/19
 */
@Module
public final class ApiModule {
    @Provides
    final ApiService provideApiService() {
        Retrofit retrofit = (new Retrofit.Builder())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://sismola.com/api/")
                .build();
        return retrofit.create(ApiService.class);
    }
}
