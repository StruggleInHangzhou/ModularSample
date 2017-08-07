package com.gavel.core.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jhhuang on 2017/3/19.
 * QQ:781913268
 * Description：xxx
 */
public class ParamUtils
{
    public static <T> String createByType(T t)
    {
        String param = "";
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            try
            {
                field.setAccessible(true);
                param += field.getName() + "=" + field.get(t).toString() + "&";

            } catch (Exception e)
            {
            }
        }
        if (param.length() > 0)
        {
            param = param.substring(0, param.length() - 1);
        }
        return param;
    }

    public static <T> Map<String, String> objectToMap(T t)
    {
        Map<String, String> map = new HashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            try
            {
                field.setAccessible(true);
                if (!TextUtils.isEmpty(field.get(t).toString()))
                {
                    map.put(field.getName(), field.get(t).toString());
                }
            } catch (Exception e)
            {
            }
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

    public static boolean isDataString(String data)
    {
        if (TextUtils.isEmpty(data))
        {
            return false;
        }
        String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(data);
        return m.matches();
    }

    public static boolean verifyLoginPwd(String password)
    {
//        String eL = "^(?![0-9]+$)(?![a-zA-Z]+$)";
        String eL = "^(?![^a-zA-Z]+$)(?!\\\\D+$).{6,20}$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean verifyPayPwd(String password)
    {
//        String eL = "^(?![0-9]+$)(?![a-zA-Z]+$)";
        String eL = "^(?![^a-zA-Z]+$)(?!\\\\D+$).{8,20}$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
