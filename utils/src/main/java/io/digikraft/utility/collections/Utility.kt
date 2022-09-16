package io.digikraft.utility.collections


fun <T> List<T>.copy(): List<T> {
    return mutableListOf<T>().apply {
        addAll(this)
    }
}