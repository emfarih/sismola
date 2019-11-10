package com.example.sismola.presentation.viewmodel;

import android.os.Build;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;
/**
 * @author by M on 10/11/19
 */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;
    @SuppressWarnings("unchecked")
    @NotNull
    public ViewModel create(@NotNull Class modelClass) {
        Provider<ViewModel> provider = this.creators.get(modelClass);
        Object var12 = null;
        if (provider == null) {
            Iterable<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> $this$firstOrNull$iv = this.creators.entrySet();
            Iterator<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> var5 = $this$firstOrNull$iv.iterator();
            while(true) {
                if (!var5.hasNext()) {
                    break;
                }
                Object element$iv = var5.next();
                Map.Entry it = (Map.Entry)element$iv;
                if (modelClass.isAssignableFrom((Class)it.getKey())) {
                    var12 = element$iv;
                    break;
                }
            }
            provider = var12 != null ? (Provider<ViewModel>)((Map.Entry)var12).getValue() : null;
        }
        Provider<ViewModel> creator = provider;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            var12 = Objects.requireNonNull(creator).get();
        }
        //noinspection ConstantConditions
        return (ViewModel)var12;
    }

    @Inject
    ViewModelFactory(@NotNull Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }
}
