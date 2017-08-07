package com.gavel.http.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by jhhuang on 2016/12/15.
 * QQ:781913268
 * Description：retorfit 工具类
 */
public class TransformUtils
{
    public static RequestBody getRequestBody(String content)
    {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
    }

    public static Map<String, RequestBody> filesToMultipart(String key, List<File> files)
    {
        Map<String, RequestBody> bodymap = new HashMap<>();
        for (File file : files)
        {
            bodymap.put(key + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file));
        }
        return bodymap;
    }

    public static Map<String, RequestBody> filesToMultipart(String key, File file)
    {
        Map<String, RequestBody> bodymap = new HashMap<>();
        bodymap.put(key + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file));
        return bodymap;
    }

    private static String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try
        {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}