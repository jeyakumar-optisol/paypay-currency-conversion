package io.paypay.utility.nullablity

//?.let{}.orElse{}
inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}