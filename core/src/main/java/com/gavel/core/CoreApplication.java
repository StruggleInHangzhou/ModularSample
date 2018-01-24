package com.gavel.core;

import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.gavel.core.di.component.AppComponent;
import com.gavel.core.di.component.DaggerAppComponent;
import com.gavel.core.di.moudle.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


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
        Utils.init(this);
        initLog();
        super.onCreate();
        instance = this;
        CrashHandler.getInstance().init(instance);
        ARouter.init(this);

    }

    protected void initLog()
    {
        if (!isDebug())
        {
            return;
        }
        ARouter.openLog();
        ARouter.openDebug();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
//                .methodCount(0)         // (Optional) How many method line to show. Default 2
//                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(AppUtils.getAppPackageName())   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();


        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy)
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
