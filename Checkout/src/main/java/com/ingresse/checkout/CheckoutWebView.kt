package com.ingresse.checkout

import android.content.Context
import androidx.compose.runtime.Composable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class CheckoutWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val webView: WebView = WebView(context).apply {
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val customUrl = request?.url.toString()
                if (customUrl.startsWith("gateway://")) {
                    // Handle the deep link yourself
                    handleDeepLink(customUrl)
                    return true
                } else {
                    // Let the WebView handle other URLs
                    return false
                }
            }
        }
        webChromeClient = WebChromeClient()
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true // Enables DOM storage API for HTML5
        settings.useWideViewPort = true // Makes the WebView have a normal viewport (such as a normal desktop browser)
        settings.loadWithOverviewMode = true // Loads the WebView completely zoomed out
        settings.builtInZoomControls = true // Allows zooming
        settings.displayZoomControls = false // Hides the zoom controls
        isFocusable = true
        isFocusableInTouchMode = true
    }

    init {
        addView(webView)
    }

    fun loadUrlWithParams(params: CheckoutParams) {
        val urlBuilder = Uri.parse(params.url).buildUpon()
        params.token.let { urlBuilder.appendQueryParameter("token", it) }
        params.locale?.let { urlBuilder.appendQueryParameter("locale", it) }
        params.textColorPrimary?.let { urlBuilder.appendQueryParameter("textColorPrimary", it) }
        params.textColorSecondary?.let { urlBuilder.appendQueryParameter("textColorSecondary", it) }
        params.textColorHighlight?.let { urlBuilder.appendQueryParameter("textColorHighlight", it) }
        params.textColorAccent?.let { urlBuilder.appendQueryParameter("textColorAccent", it) }
        params.bgColorPrimary?.let { urlBuilder.appendQueryParameter("bgColorPrimary", it) }
        params.bgColorSecondary?.let { urlBuilder.appendQueryParameter("bgColorSecondary", it) }
        params.bgColorAccent?.let { urlBuilder.appendQueryParameter("bgColorAccent", it) }
        params.borderColorPrimary?.let { urlBuilder.appendQueryParameter("borderColorPrimary", it) }
        params.borderColorAccent?.let { urlBuilder.appendQueryParameter("borderColorAccent", it) }
        params.buttonColorPrimary?.let { urlBuilder.appendQueryParameter("buttonColorPrimary", it) }
        params.buttonColorHover?.let { urlBuilder.appendQueryParameter("buttonColorHover", it) }
        // Add other optional parameters here, following the same pattern

        webView.loadUrl(urlBuilder.build().toString())
    }

    private fun handleDeepLink(url: String) {
        val uri = Uri.parse(url)
        val status = uri.getQueryParameter("status")
        Log.d("STATUS", status.toString())
        // Process the 'status' and perform actions as needed
    }
}

@Composable
fun CheckoutWebViewComposable(
    params: CheckoutParams,
    modifier: Modifier = Modifier
) {
    var url by remember { mutableStateOf("") }

    LaunchedEffect(params) {
        val urlBuilder = Uri.parse(params.url).buildUpon()
        params.token.let { urlBuilder.appendQueryParameter("token", it) }
        params.locale?.let { urlBuilder.appendQueryParameter("locale", it) }
        params.textColorPrimary?.let { urlBuilder.appendQueryParameter("textColorPrimary", it) }
        params.textColorSecondary?.let { urlBuilder.appendQueryParameter("textColorSecondary", it) }
        params.textColorHighlight?.let { urlBuilder.appendQueryParameter("textColorHighlight", it) }
        params.textColorAccent?.let { urlBuilder.appendQueryParameter("textColorAccent", it) }
        params.bgColorPrimary?.let { urlBuilder.appendQueryParameter("bgColorPrimary", it) }
        params.bgColorSecondary?.let { urlBuilder.appendQueryParameter("bgColorSecondary", it) }
        params.bgColorAccent?.let { urlBuilder.appendQueryParameter("bgColorAccent", it) }
        params.borderColorPrimary?.let { urlBuilder.appendQueryParameter("borderColorPrimary", it) }
        params.borderColorAccent?.let { urlBuilder.appendQueryParameter("borderColorAccent", it) }
        params.buttonColorPrimary?.let { urlBuilder.appendQueryParameter("buttonColorPrimary", it) }
        params.buttonColorHover?.let { urlBuilder.appendQueryParameter("buttonColorHover", it) }
        // Add other optional parameters here, following the same pattern

        url = urlBuilder.build().toString()
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val customUrl = request?.url.toString()
                        if (customUrl.startsWith("gateway://")) {
                            handleDeepLink(customUrl)
                            return true
                        } else {
                            return false
                        }
                    }
                }
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.useWideViewPort = true
                settings.loadWithOverviewMode = true
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                isFocusable = true
                isFocusableInTouchMode = true

                loadUrl(url)
            }
        },
        modifier = modifier,
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}

private fun handleDeepLink(url: String) {
    val uri = Uri.parse(url)
    val status = uri.getQueryParameter("status")
    Log.d("STATUS", status.toString())
    // Process the 'status' and perform actions as needed
}