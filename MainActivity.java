package com.qahwagypaha;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private static final String WEBSITE_URL = "https://qahwajialpaha.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        setupWebView();
        loadWebsite();

        // Subscribe to a general topic for push notifications
        FirebaseMessaging.getInstance().subscribeToTopic("promotions")
            .addOnCompleteListener(task -> {
                String msg = "Subscribed to promotions";
                if (!task.isSuccessful()) {
                    msg = "Subscription failed";
                }
                // Optional: show a toast message for confirmation
                // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            });
    }

    private void setupWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void loadWebsite() {
        webView.loadUrl(WEBSITE_URL);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
