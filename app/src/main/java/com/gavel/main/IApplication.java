package com.gavel.main;

import com.gavel.core.CoreApplication;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

public class IApplication extends CoreApplication
{

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    protected boolean isDebug()
    {
        return BuildConfig.DEBUG;
    }
}
