package io.paypay.domain.model


import androidx.annotation.Keep

@Keep
class ResponseLatestCurrency(
    var base: String,
    var disclaimer: String,
    var license: String,
    var rates: List<Float>,
    var timestamp: Int
) 