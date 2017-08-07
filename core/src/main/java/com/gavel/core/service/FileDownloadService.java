package com.gavel.core.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.gavel.core.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jhhuang on 2016/7/15.
 * QQ:781913268
 * Description：下载更新服务
 */
public class FileDownloadService extends Service
{

    public final static String DEFAULT_SAVE_FILE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "loan"
            + File.separator
            + "download"
            + File.separator;

    public static final String BUNDLE_KEY_DOWNLOAD_URL = "download_url";

    public static final String BUNDLE_KEY_TITLE = "title";

    private final String tag = "download";
    private static final int NOTIFY_ID = 0;
    private int progress;
    private NotificationManager mNotificationManager;
    private boolean canceled;

    private String downloadUrl;

    private String mTitle = "正在下载%s";

    private String saveFileName = DEFAULT_SAVE_FILE_PATH;

    private ICallbackResult callback;

    private DownloadBinder binder;

    private boolean serviceIsDestroy = false;

    private Context mContext = this;

    private Thread downLoadThread;

    private Notification mNotification;

    private Handler mHandler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    // 下载完毕
                    mNotificationManager.cancel(NOTIFY_ID);
                    installApk();
                    break;
                case 2:
                    // 取消通知
                    mNotificationManager.cancel(NOTIFY_ID);
                    break;
                case 1:
                    int rate = msg.arg1;
                    if (rate < 100)
                    {
                        RemoteViews contentview = mNotification.contentView;
                        contentview.setTextViewText(R.id.tv_download_state, mTitle + "(" + rate
                                + "%" + ")");
                        contentview.setProgressBar(R.id.pb_download, 100, rate,
                                false);
                    } else
                    {
                        // 下载完毕后变换通知形式
                        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                        mNotification.contentView = null;
                        Intent intent = new Intent();
                        // 告知已完成
                        intent.putExtra("completed", "yes");
                        // 更新参数,注意flags要使用FLAG_UPDATE_CURRENT
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                mContext, 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
//					mNotification.setLatestEventInfo(mContext, "下载完成",
//							"文件已下载完毕", contentIntent);
                        serviceIsDestroy = true;
                        stopSelf();// 停掉服务自身
                    }
                    mNotificationManager.notify(NOTIFY_ID, mNotification);
                    break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent)
    {
        downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
        saveFileName = saveFileName + getSaveFileName(downloadUrl);
        mTitle = String.format(mTitle, intent.getStringExtra(BUNDLE_KEY_TITLE));
        return binder;
    }

    private String getSaveFileName(String downloadUrl)
    {
        if (downloadUrl == null || downloadUrl.length() == 0)
        {
            return "";
        }
        return downloadUrl.substring(downloadUrl.lastIndexOf("/"));
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        binder = new DownloadBinder();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        stopForeground(true);// 这个不确定是否有作用
    }

    private void startDownload()
    {
        canceled = false;
        downloadApk();
    }

    /**
     * 创建通知
     */
    private void setUpNotification()
    {
        int icon = R.mipmap.ic_launcher;
        CharSequence tickerText = "准备下载";
        long when = System.currentTimeMillis();
        mNotification = new Notification(icon, tickerText, when);

        // 放置在"正在运行"栏目中
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;

        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.core_download_notification_show);
        contentView.setTextViewText(R.id.tv_download_state, mTitle);
        // 指定个性化视图
        mNotification.contentView = contentView;

        Intent intent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 指定内容意图
        mNotification.contentIntent = contentIntent;
        mNotificationManager.notify(NOTIFY_ID, mNotification);
    }

    private void downloadApk()
    {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk()
    {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists())
        {
            return;
        }
        installAPK(mContext, apkfile);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try
        {
            downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
            saveFileName = saveFileName + getSaveFileName(downloadUrl);
            mTitle = String.format(mTitle, intent.getStringExtra(BUNDLE_KEY_TITLE));
            start();
        } catch (Exception e)
        {
            e.printStackTrace();
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void start()
    {
        if (downLoadThread == null || !downLoadThread.isAlive())
        {
            progress = 0;
            setUpNotification();
            new Thread()
            {
                public void run()
                {
                    // 下载
                    startDownload();
                }

                ;
            }.start();
        }
    }

    private void installAPK(Context context, File file)
    {
        if (file == null || !file.exists())
            return;
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    private Runnable mdownApkRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            File file = new File(DEFAULT_SAVE_FILE_PATH);
            if (!file.exists())
            {
                file.mkdirs();
            }
            String apkFile = saveFileName;
            File saveFile = new File(apkFile);
            try
            {
                downloadUpdateFile(downloadUrl, saveFile);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };

    public long downloadUpdateFile(String downloadUrl, File saveFile)
            throws Exception
    {
        int downloadCount = 0;
        int currentSize = 0;
        long totalSize = 0;
        int updateTotalSize = 0;

        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try
        {
            URL url = new URL(downloadUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection
                    .setRequestProperty("User-Agent", "PacificHttpClient");
            if (currentSize > 0)
            {
                httpConnection.setRequestProperty("RANGE", "bytes="
                        + currentSize + "-");
            }
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(20000);
            updateTotalSize = httpConnection.getContentLength();
            if (httpConnection.getResponseCode() == 404)
            {
                throw new Exception("fail!");
            }
            is = httpConnection.getInputStream();
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[1024];
            int readsize = 0;
            while ((readsize = is.read(buffer)) > 0)
            {

                fos.write(buffer, 0, readsize);
                totalSize += readsize;
                // 为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
                if ((downloadCount == 0)
                        || (int) (totalSize * 100 / updateTotalSize) - 4 > downloadCount)
                {
                    downloadCount += 4;
                    // 更新进度
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    msg.arg1 = downloadCount;
                    mHandler.sendMessage(msg);
                    if (callback != null)
                        callback.OnBackResult(progress);
                }
            }

            // 下载完成通知安装
            mHandler.sendEmptyMessage(0);
            // 下载完了，cancelled也要设置
            canceled = true;

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (httpConnection != null)
            {
                httpConnection.disconnect();
            }
            if (is != null)
            {
                is.close();
            }
            if (fos != null)
            {
                fos.close();
            }
        }
        return totalSize;
    }

    public class DownloadBinder extends Binder
    {
        public void start()
        {
            if (downLoadThread == null || !downLoadThread.isAlive())
            {
                progress = 0;
                setUpNotification();
                new Thread()
                {
                    public void run()
                    {
                        // 下载
                        startDownload();
                    }

                    ;
                }.start();
            }
        }

        public void cancel()
        {
            canceled = true;
        }

        public int getProgress()
        {
            return progress;
        }

        public boolean isCanceled()
        {
            return canceled;
        }

        public boolean serviceIsDestroy()
        {
            return serviceIsDestroy;
        }

        public void cancelNotification()
        {
            mHandler.sendEmptyMessage(2);
        }

        public void addCallback(ICallbackResult callback)
        {
            FileDownloadService.this.callback = callback;
        }
    }
}
