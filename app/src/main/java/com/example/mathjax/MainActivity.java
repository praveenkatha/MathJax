package com.example.mathjax;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private ProgressBar progressBar;

    class MathJaxCallBackInterface {

        MathJaxCallBackInterface(Context c) {
        }

        @JavascriptInterface
        public void onRenderingComplete() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showWebView(true);
                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        showWebView(false);
        myWebView.loadUrl("file:///android_asset/hello.html");
        WebSettings webSettings = myWebView.getSettings();
        myWebView.addJavascriptInterface(new MathJaxCallBackInterface(this), "Android");
        webSettings.setJavaScriptEnabled(true);
    }

    private void showWebView(boolean show) {
        myWebView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(show ? View.GONE : View.VISIBLE);

    }

}

