package com.webview.wheel_spin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private static String weburl;
    String cookies="";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://scp-traff33.com/l/616c707e7424e73d2f072a6b");

        WebViewClient webViewClient = new WebViewClient() {
            @SuppressWarnings("deprecation") @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @TargetApi(Build.VERSION_CODES.N) @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebViewClient(webViewClient);

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });



        cookies= CookieManager.getInstance().getCookie("https://broslotsonline.pw/Mpj8N2cR");
        Log.d("cookies = ", cookies+"");

        clearCookies();

        cookies= CookieManager.getInstance().getCookie("https://broslotsonline.pw/Mpj8N2cR");
        Log.d("cookies after clear = ", cookies+"");
    }
    public static void clearCookies(){
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}