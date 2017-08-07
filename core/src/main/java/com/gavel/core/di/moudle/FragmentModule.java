package com.gavel.core.di.moudle;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.gavel.core.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */
@Module
public class FragmentModule
{
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public AppCompatActivity provideActivity() {
        return (AppCompatActivity) fragment.getActivity();
    }
}