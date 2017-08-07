package com.gavel.core;

import android.content.Intent;
import android.os.Bundle;

import com.gavel.core.component.IRxActivity;
import com.jiahuaandroid.basetools.utils.ActivityCollector;


/**
 * Created by jhhuang on 2017/2/22.
 * QQ:781913268
 * Description：CoreActivity
 */
public class CoreActivity extends IRxActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
}