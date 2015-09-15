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

    String data = "<span class=\\\"definition nb_hidden clAnnotationDecoration\\\">\n" +
            "A standardized code for\n" +
            "representing\n" +
            "      <span\n" +
            "              class=\\\"inlineMath\\\" id=\\\"PWJDLT933186870\\\">\n" +
            "        <math display=\\\"inline\\\">\n" +
            "            <mrow>\n" +
            "                <msub>\n" +
            "                    <mi mathvariant=\\\"normal\\\">C</mi>\n" +
            "                    <mrow>\n" +
            "                        <mn>10</mn>\n" +
            "                    </mrow>\n" +
            "                </msub>\n" +
            "                <msub>\n" +
            "                    <mi mathvariant=\\\"normal\\\">H</mi>\n" +
            "                    <mn>6</mn>\n" +
            "                </msub>\n" +
            "                <msub>\n" +
            "                    <mi mathvariant=\\\"normal\\\">O</mi>\n" +
            "                    <mn>3</mn>\n" +
            "                </msub>\n" +
            "            </mrow>\n" +
            "        </math>\n" +
            "      </span>\n" +
            "a chemical element or compound\n" +
            "</span>";

    String imgData = "<div id='imagecontainer'>" +
            "           <img id='flashcard_image' " +
            "src='http://www.gettyimages.co.uk/gi-resources/images/Homepage/Category-Creative/UK/UK_Creative_462809583.jpg'>" +
            "</div>";

    String textData = "Flashcard";

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

        @JavascriptInterface
        public String getHtmlData() {
            return textData;
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
        myWebView.loadUrl("javascript:loadHtml()");
    }

    private void showWebView(boolean show) {
        myWebView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(show ? View.GONE : View.VISIBLE);

    }

}

