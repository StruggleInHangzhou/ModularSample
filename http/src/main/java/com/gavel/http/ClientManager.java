package com.gavel.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jhhuang on 2016/9/21.
 * QQ:781913268
 * Description：客户端管理类
 */
public class ClientManager
{
    private static Map<String, RetorfitClient> clientMap = new HashMap<>();

    private ClientManager()
    {
    }

    public static RetorfitClient getClient(String url)
    {
        RetorfitClient appClient;
        appClient = clientMap.get(url);

        if (appClient == null)
        {
            try
            {
                appClient = new RetorfitClient(url);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            clientMap.put(url, appClient);
        }

        return appClient;
    }

    public static RetorfitClient getClient()
    {
        return getClient(RetorfitClient.URL_BASE);
    }

    public static void registerClient(String url)
    {
        if (!clientMap.containsKey(url))
        {
            RetorfitClient client = new RetorfitClient(url);
            clientMap.put(url, client);
        }
    }

}
