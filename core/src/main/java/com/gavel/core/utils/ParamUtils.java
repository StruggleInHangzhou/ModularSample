package com.gavel.core.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jhhuang on 2017/3/19.
 * QQ:781913268
 * Description：xxx
 */
public class ParamUtils
{
    public static <T> String createByType(T t)
    {
        return createByMap(objectToMap(t));
    }

    public static <T> Map<String, String> objectToMap(T t)
    {
        Map<String, String> map = new HashMap<>();
        Class clazz = t.getClass();
        while (null != clazz && !clazz.getName().toLowerCase().equals("java.lang.object"))
        {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields)
            {
                try
                {
                    field.setAccessible(true);
                    if (!TextUtils.isEmpty(field.get(t).toString()))
                    {
                        map.put(field.getName(), field.get(t).toString());
                    } else
                    {
                        map.put(field.getName(), "");
                    }
                } catch (Exception e)
                {
                }
            }
            clazz = clazz.getSuperclass();
        }
        return map;
    }

    public static String createByMap(Map<String, String> map)
    {
        String param = "";
        Set<String> keys = map.keySet();
        for (String key : keys)
        {
            param += key + "=" + map.get(key) + "&";
        }
        if (param.length() > 0)
        {
            param = param.substring(0, param.length() - 1);
        }
        return param;
    }

}
