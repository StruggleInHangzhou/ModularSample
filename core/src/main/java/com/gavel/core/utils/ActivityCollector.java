package com.gavel.core.utils;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhhuang on 2016/4/26.
 * 作用：自定义的活动栈
 */
public class ActivityCollector {

    private static List<AppCompatActivity> activities = new ArrayList<>();

    public static void addActivity(AppCompatActivity appCompatActivity) {
        if(!activities.contains(appCompatActivity)) {
            activities.add(appCompatActivity);
        }
    }

    public static void removeActivity(AppCompatActivity appCompatActivity) {
        activities.remove(appCompatActivity);
    }

    public static void finishAll() {
        for (AppCompatActivity appCompatActivity : activities) {
            if(!appCompatActivity.isFinishing()) {
                appCompatActivity.finish();
            }
        }
    }

}
