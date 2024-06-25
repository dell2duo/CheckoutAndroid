package com.example.androidexemple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.androidexemple.ui.theme.AndroidExempleTheme
import com.ingresse.checkout.CheckoutWebViewComposable
import com.ingresse.checkout.CheckoutParams

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidExempleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CheckoutWebViewComposable(
                        params = CheckoutParams(
                            url = "https://b2c9-2804-2484-8459-7d00-6573-8ea9-91fa-d925.ngrok-free.app/checkout",
                            token = "SFMyNTY.g2gDbQAAACQ3MDNlMjVlMi01OGIzLTRiNjMtYjY5Mi01OWE2NTY4OTY5ZmRuBgDlPulPkAFiAAFRgA.Opp-6cd1UN7Y6iNoBmSf-l9hnWJ67cb_TpX5V0uiiqI",
                        )
                    )
                }
            }
        }
    }
}
