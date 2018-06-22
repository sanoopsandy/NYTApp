package com.example.nytapp.webViewModule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.example.nytapp.R
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl(intent.getBundleExtra("Web").getString("url"))
        webView.setHorizontalScrollBarEnabled(false)

    }
}