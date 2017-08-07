package com.gavel.core.di.moudle;


import com.gavel.core.CoreApplication;
import com.gavel.core.di.ContextLife;
import com.gavel.db.THDevOpenHelper;
import com.gavel.db.generated.DaoMaster;
import com.gavel.db.generated.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */
@Module
public class AppModule
{
    private final CoreApplication application;

    public AppModule(CoreApplication application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    CoreApplication provideApplicationContext()
    {
        return application;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession()
    {
        return new DaoMaster(new THDevOpenHelper(application, "app_db", null).getWritableDatabase()).newSession();
    }
}
