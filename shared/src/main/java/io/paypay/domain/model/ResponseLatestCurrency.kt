package io.paypay.domain.model


import androidx.annotation.Keep
import com.google.gson.JsonObject

@Keep
class ResponseLatestCurrency(
    var base: String,
    var disclaimer: String,
    var license: String,
    var rates: JsonObject,
    var timestamp: Int
) 