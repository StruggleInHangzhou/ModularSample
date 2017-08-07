package com.gavel.core.di.moudle;


import android.support.v7.app.AppCompatActivity;

import com.gavel.core.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

@Module
public class ActivityModule
{
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity)
    {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public AppCompatActivity provideActivity()
    {
        return mActivity;
    }
}
