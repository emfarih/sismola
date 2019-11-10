package com.example.sismola.di;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import dagger.MapKey;

/**
 * @author by M on 10/11/19
 */
@MapKey
@Documented
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.METHOD})
public @interface ViewModelKey {
    Class value();
}