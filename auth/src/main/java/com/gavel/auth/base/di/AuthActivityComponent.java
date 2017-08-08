package com.gavel.auth.base.di;


import android.support.v7.app.AppCompatActivity;

import com.gavel.auth.ui.activity.welcome.WelComeActivity;
import com.gavel.core.di.ActivityScope;
import com.gavel.core.di.component.AppComponent;
import com.gavel.core.di.moudle.ActivityModule;

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

    void inject(WelComeActivity activity);
}