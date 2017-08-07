package com.gavel.core;

import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.gavel.core.di.component.AppComponent;
import com.gavel.core.di.component.DaggerAppComponent;
import com.gavel.core.di.moudle.AppModule;
import com.jiahuaandroid.basetools.utils.CUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by jhhuang on 2017/4/10
 * QQ:781913268
 * Description：xxx
 */
public abstract class CoreApplication extends MultiDexApplication
{
    private static CoreApplication instance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        CrashHandler.getInstance().init(instance);
        //工具加载
        CUtils.init(this);
        Utils.init(this);
        ARouter.init(this);

        if (isDebug())
        {
            ARouter.openLog();
            ARouter.openDebug();
        }

        Logger.addLogAdapter(new AndroidLogAdapter()
        {
            @Override
            public boolean isLoggable(int priority, String tag)
            {
                return isDebug();
            }
        });

    }

    protected abstract boolean isDebug();

    public static AppComponent getAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }


}
