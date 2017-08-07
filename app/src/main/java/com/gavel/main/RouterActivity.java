package com.gavel.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class RouterActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation(this, new NavigationCallback()
        {
            @Override
            public void onFound(Postcard postcard)
            {
                finish();
            }

            @Override
            public void onLost(Postcard postcard)
            {
                finish();
            }

        });
    }
}
