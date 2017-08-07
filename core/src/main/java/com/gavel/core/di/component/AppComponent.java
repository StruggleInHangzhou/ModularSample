package com.gavel.core.di.component;


import com.gavel.core.CoreApplication;
import com.gavel.core.di.ContextLife;
import com.gavel.core.di.moudle.AppModule;
import com.gavel.db.generated.DaoSession;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent
{
    /**
     * @return 提供AppApplication
     */
    @ContextLife("Application")
    CoreApplication getContext();

    DaoSession daoSessionHHelper();

}
