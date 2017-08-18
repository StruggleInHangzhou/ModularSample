package com.gavel.main.base.di;


import android.support.v7.app.AppCompatActivity;

import com.gavel.core.di.FragmentScope;
import com.gavel.core.di.component.AppComponent;
import com.gavel.core.di.moudle.FragmentModule;

import dagger.Component;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface AuthFragmentComponent
{
    AppCompatActivity getActivity();
}

