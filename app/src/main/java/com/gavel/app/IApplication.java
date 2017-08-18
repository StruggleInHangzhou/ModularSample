package com.gavel.app;

import com.gavel.core.CoreApplication;
import com.gavel.main.BuildConfig;

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
