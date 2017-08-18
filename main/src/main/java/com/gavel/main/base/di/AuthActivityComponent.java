package com.gavel.main.base.di;


import android.support.v7.app.AppCompatActivity;

import com.gavel.core.di.ActivityScope;
import com.gavel.core.di.component.AppComponent;
import com.gavel.core.di.moudle.ActivityModule;
import com.gavel.main.MainActivity;

import dagger.Component;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface AuthActivityComponent
{
    AppCompatActivity getActivity();

    void inject(MainActivity activity);
}