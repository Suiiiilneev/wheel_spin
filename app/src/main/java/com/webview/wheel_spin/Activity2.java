package com.webview.wheel_spin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Activity2 extends AppCompatActivity {

    private static final String [] sectors = {"1","2","3","4","5","6","7","8"};
    private static final int [] sectorDegrees = new int[sectors.length];
    private static final Random random = new Random();
    private int degree = 0;
    private boolean isSpinning = false;
    private ImageView wheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://broslotsonline.pw/Mpj8N2cR");

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

        final ImageView spin = findViewById(R.id.spin);
        wheel = findViewById(R.id.wheel);

        getDegreeForSectors();

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSpinning){
                    spin();
                    isSpinning = true;
                }

            }
        });
    }
    private void spin(){
        degree = random.nextInt(sectors.length-1);
        RotateAnimation rotateAnimation = new RotateAnimation(0, (360 * sectors.length) + sectorDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(Activity2.this, "Вы получили " +sectors[sectors.length -(degree + 1)] + " очков!", Toast.LENGTH_SHORT).show();
                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheel.startAnimation(rotateAnimation);
    }

    private void getDegreeForSectors(){
        int sectorDegree = 360/sectors.length;
        for(int i=0; i< sectors.length; i++){


            sectorDegrees[i] = (i+1) * sectorDegree;
        }
    }
}
