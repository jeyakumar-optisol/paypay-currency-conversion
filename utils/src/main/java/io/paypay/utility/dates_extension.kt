package io.paypay.utility

import java.text.SimpleDateFormat
import java.util.*

const val UI_DATE_FORMAT = "EEE, dd MMM"
const val API_DATE_FORMAT = "yyyy-MM-dd"

fun Date.toStringFormatted(format: String = "EEE, dd MMM", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTimeFormatted(): String {
    return Calendar.getInstance().time.toStringFormatted(UI_DATE_FORMAT)
}

fun getCurrentDateTimeAPI(): String {
    return Calendar.getInstance().time.toStringFormatted(API_DATE_FORMAT)
}

fun getCurrentDate(): Calendar {
    return Calendar.getInstance()
}

fun Calendar.getDateFormatted(): String {
    return this.time.toStringFormatted("EEE, dd MMM")
}

fun Calendar.getPreviousDate(): Calendar {
    this.add(Calendar.DAY_OF_YEAR, -1)
    return this
}

fun Calendar.getNextDate(): Calendar {
    this.add(Calendar.DAY_OF_YEAR, 1)
    return this
}

fun String.toApiFormat(
    fromFormat: String = UI_DATE_FORMAT,
    toFormat: String = API_DATE_FORMAT,
    locale: Locale = Locale.getDefault()
): String {
    val df = SimpleDateFormat(fromFormat, locale)
    val secondFormat = SimpleDateFormat(toFormat, locale)
    return secondFormat.format(df.parse(this)).toString()
}

fun String.toUIDateFormat(
    fromFormat: String = API_DATE_FORMAT,
    toFormat: String = UI_DATE_FORMAT,
    locale: Locale = Locale.getDefault()
): String {
    val df = SimpleDateFormat(fromFormat, locale)
    val secondFormat = SimpleDateFormat(toFormat, locale)
    return secondFormat.format(df.parse(this)).toString()
}

fun Calendar.toApiFormat(): String {
    return this.time.toStringFormatted(API_DATE_FORMAT)
}

fun String.formatDateHomeItems(
    fromFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
    toFormat: String = UI_DATE_FORMAT,
    locale: Locale = Locale.getDefault()
): String {
    val df = SimpleDateFormat(fromFormat, locale)
    val secondFormat = SimpleDateFormat(toFormat, locale)
    return secondFormat.format(df.parse(this)).toString()
}

fun String.formatDate(fromFormat: String, toFormat: String = UI_DATE_FORMAT, locale: Locale = Locale.getDefault()): String {
    val df = SimpleDateFormat(fromFormat, locale)
    val secondFormat = SimpleDateFormat(toFormat, locale)
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        secondFormat.format(df.parse(this)!!).toString()
    } else {
        this
    }
}