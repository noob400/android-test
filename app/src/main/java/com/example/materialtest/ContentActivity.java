package com.example.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContentActivity extends AppCompatActivity {

    WebView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        content=(WebView) findViewById(R.id.response);
        content.getSettings().setJavaScriptEnabled(true);
        content.getSettings().setBuiltInZoomControls(true);
        content.getSettings().setSupportZoom(true);
        content.getSettings().setUseWideViewPort(true);
        content.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        content.getSettings().setLoadWithOverviewMode(true);
        content.setWebViewClient(new WebViewClient());
        content.loadUrl("http://www.glut.edu.cn/");

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && content.canGoBack()) {
            content.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }

}
