package com.example.kotlin_webview

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val url by remember {
        mutableStateOf("https://www.youtube.com")
    }

    val webView by remember {
        mutableStateOf<WebView?>(null)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        WebViewScreen(url, webView)
    }
}


@Composable
fun WebViewScreen(
    url:String,
    webView: WebView?,
    modifier: Modifier = Modifier
) {

    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        webView?.loadUrl(url)
    }
    )
}



@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}