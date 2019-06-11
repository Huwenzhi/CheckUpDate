package com.hu_sir.checkvbyhu.util;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;


import com.hu_sir.checkvbyhu.listener.HttpManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


/**
 * Created by Vector
 * on 2017/6/19 0019.
 */

public class UpdateAppHttpUtil implements HttpManager , Serializable {
    private FileCallback callback;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    callback.onBefore();
                    break;
                case 1:
                    int len = msg.arg1;
                    int maxlen = msg.arg2;
//                    callback.onProgress((float) (len / maxlen), (long) (maxlen));

                    callback.onProgress( len* 1.0f/maxlen,83444);
                    break;
                case 2:
                    File file = (File) msg.obj;
                    callback.onResponse(file);
                    break;
                case 3:
                    String s = (String) msg.obj;
                    callback.onError(s);
                    break;


            }


        }
    };

    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {

    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {

    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    @Override
    public void download(@NonNull final String url, @NonNull final String path, @NonNull final String fileName, @NonNull final FileCallback callback) {
        this.callback = callback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFile(url, path, fileName);
            }
        }).start();
    }


    /**
     * @param urlPath     下载路径
     * @param downloadDir 下载存放目录
     * @param
     * @return 返回下载文件
     */
    public void downloadFile(String urlPath, String downloadDir, String fileName) {
        File file = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        BufferedInputStream bin = null;
        OutputStream out = null;

        try {
            // 统一资源
            url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
//            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
//            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(10000);
            //判断请求码是不是200
            handler.sendEmptyMessage(0);
            if (httpURLConnection.getResponseCode() == 200) {
                // 文件大小
                int fileLength = httpURLConnection.getContentLength();
                // 文件名

                String path = downloadDir + File.separatorChar + fileName;
                bin = new BufferedInputStream(httpURLConnection.getInputStream());
                file = new File(path);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                out = new FileOutputStream(file);
                int size = 0;
                int len = 0;
                byte[] buf = new byte[1024];
                while ((size = bin.read(buf)) != -1) {
                    len += size;
                    out.write(buf, 0, size);
//                    callback.onProgress((float) (len / fileLength), (long) fileLength);
                    Message messageP=new Message();
                    messageP.arg1 = len;
                    messageP.arg2 = fileLength;
                    messageP.what = 1;
                    handler.sendMessage(messageP);
                    // 打印下载百分比
                    // System.out.println("下载了-------> " + len * 100 / fileLength +
                    // "%\n");
                }
//                callback.onResponse(file);
                Message msgFile =new Message();
                msgFile.obj = file;
                msgFile.what = 2;
                handler.sendMessage(msgFile);
                bin.close();
                out.close();
            } else {
                //要关闭 请求链接
                try {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    sendError(e.getMessage());
                }
                sendError(String.valueOf(httpURLConnection.getRequestMethod()));
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sendError(e.getMessage());
//            callback.onError(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            sendError(e.getMessage());
//            callback.onError(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendError(e.getMessage());
            }
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendError(e.getMessage());
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                sendError(e.getMessage());
            }
        }
    }

    private void sendError(String message) {
        Message msg=new Message();
        msg.obj = message;
        msg.what = 3;
        handler.sendMessage(msg);
    }

}

