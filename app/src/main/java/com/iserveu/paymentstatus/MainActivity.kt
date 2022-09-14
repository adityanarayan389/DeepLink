package com.iserveu.paymentstatus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import java.net.URLEncoder


class MainActivity : AppCompatActivity() {
    lateinit var textview: TextView
    lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        try {
            // JSON here
        } catch (e2: JSONException) {

            e2.printStackTrace()
        } catch (e: Exception) {

            e.printStackTrace()
        }
        setContentView(R.layout.activity_main)

        textview = findViewById<TextView>(R.id.textview)
        textview.setOnClickListener {
            val webview = WebView(this)
            setContentView(webview)
            val url = "http://testngetjp.irctc.co.in/eticketing/wsapplogin"
            val postData = "wsloginId=${URLEncoder.encode("WINDPST00001", "UTF-8")}" +
                    "&wsTxnId=${URLEncoder.encode("1019546329833013248", "UTF-8")}" +
                    "&wsReturnUrl=${
                        URLEncoder.encode(
                            "https://iserveuirctc.page.link/response",
                            "UTF-8"
                        )
                    }"


            webview.settings.javaScriptEnabled = true
            webview.settings.allowContentAccess = true
            webview.settings.domStorageEnabled = true
            webview.settings.builtInZoomControls = true
            webview.settings.loadsImagesAutomatically = true
            webview.settings.loadWithOverviewMode = true
//            webView.addJavascriptInterface(WebAppInterface(this), "Android")


            webview.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageCommitVisible(view: WebView, url: String) {
                    super.onPageCommitVisible(view, url)


                    webview.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)


                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(request!!.url.toString()))
                    Log.d("intent_dat",intent.data.toString())
                    startActivity(intent)
                    return true

                }

//                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//
//
//                    if (url != null) {
//                        if (url.startsWith("http") || url.startsWith("https")) {
//                            return false
//                        }
//                    }
//                    if (url != null) {
//                        if (url.startsWith("intent")) {
//
//                            try {
//                                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
//                                startActivity(intent)
//                                val uri = intent.data
//                                val string= uri.toString();
//                                val fallbackUrl = intent.getStringExtra("browser_fallback_url")
//                                if (fallbackUrl != null) {
//                                    webview.loadUrl(fallbackUrl)
//                                    return true
//                                }
//                            } catch (e: URISyntaxException) {
//
//                            }
//                            return true
//                        }
//                    }
//                    return true
//                }

                override fun onReceivedError(
                    view: WebView,
                    request: WebResourceRequest,
                    error: WebResourceError
                ) {
                    Toast.makeText(this@MainActivity, error.toString(), Toast.LENGTH_LONG).show()
                }
            }
            webview.postUrl(url, postData.toByteArray())


//
        }
    }

    class WebAppInterface(private val mContext: Context) {

        /** Show a toast from the web page  */
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }


}