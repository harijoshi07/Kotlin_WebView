package com.example.kotlin_webview

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
        mutableStateOf("https://roozbehzarei.me")
    }

    val webView by remember {
        mutableStateOf<WebView?>(null)
    }

    var progress by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        WebViewScreen(
            url,
            webView,
            updateProgress = { currentProgress -> progress = currentProgress }
        )
        ProgressIndicator(progress)
    }
}


@Composable
fun WebViewScreen(
    url: String,
    webView: WebView?,
    updateProgress: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)

            webChromeClient = object : WebChromeClient() {
                // Pass up current loading progress to be used by ProgressIndicator function
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    updateProgress(newProgress)
                }
            }
        }
    }, update = {
        webView?.loadUrl(url)
    }
    )
}

@Composable
private fun ProgressIndicator(progress: Int) {

        for (i in 1..100) {
        LinearProgressIndicator(
            progress = { progress.toFloat() / 100 },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}