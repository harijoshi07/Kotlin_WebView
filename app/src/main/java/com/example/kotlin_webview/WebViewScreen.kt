package com.example.kotlin_webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(modifier: Modifier = Modifier) {

    val url by remember {
        mutableStateOf("https://www.github.com/harijoshi07")
    }

    val webView by remember {
        mutableStateOf<WebView?>(null)
    }

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
private fun WebViewScreenPreview() {
    WebViewScreen()
}