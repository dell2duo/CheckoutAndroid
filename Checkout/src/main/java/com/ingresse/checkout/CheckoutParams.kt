package com.ingresse.checkout

data class CheckoutParams(
    val url: String,
    val token: String,
    val locale: String? = null,
    val textColorPrimary: String? = null,
    val textColorSecondary: String? = null,
    val textColorHighlight: String? = null,
    val textColorAccent: String? = null,

    val bgColorPrimary: String? = null,
    val bgColorSecondary: String? = null,
    val bgColorAccent: String? = null,

    val borderColorPrimary: String? = null,
    val borderColorAccent: String? = null,

    val buttonColorPrimary: String? = null,
    val buttonColorHover: String? = null
)