package com.gavel.core.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ContextLife
{
    String value() default "Application";
}
