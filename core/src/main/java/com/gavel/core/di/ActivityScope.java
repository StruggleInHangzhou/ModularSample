package com.gavel.core.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope
{
}
