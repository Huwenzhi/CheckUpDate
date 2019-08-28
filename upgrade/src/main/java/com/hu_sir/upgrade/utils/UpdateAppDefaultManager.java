package com.hu_sir.upgrade.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hu_sir.upgrade.HttpManager;
import com.hu_sir.upgrade.http.CallBackUtil;
import com.hu_sir.upgrade.http.HttpURLConnectionUtil;

import java.io.File;
import java.util.Map;

public class UpdateAppDefaultManager implements HttpManager {
    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        HttpURLConnectionUtil.get(url, params, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                callBack.onError(errorMessage);
            }

            @Override
            public void onResponse(String response) {
                callBack.onResponse(response);
            }
        });

    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback
            callBack) {
        HttpURLConnectionUtil.post(url, params, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                callBack.onError(errorMessage);
            }

            @Override
            public void onResponse(String response) {
                callBack.onResponse(response);
            }
        });
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
        HttpURLConnectionUtil.downloadFile(url, new CallBackUtil.CallBackFile(path,fileName) {
            @Override
            public void onFailure(int code, String errorMessage) {
                callback.onError(errorMessage);
            }

            @Override
            public void onResponse(File response) {
                callback.onResponse(response);
            }

            @Override
            public void onProgress(float progress, long total) {
//                super.onProgress(progress, total);
                callback.onProgress(progress,total);
            }

            @Override
            public void onStart() {
                callback.onBefore();
                super.onStart();
            }
        });
    }
}
