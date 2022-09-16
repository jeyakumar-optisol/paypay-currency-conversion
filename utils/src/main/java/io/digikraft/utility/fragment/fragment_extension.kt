package io.digikraft.utility.fragment

import android.R
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

fun Fragment.goto(id: Int) {
    activity?.let {
        findNavController().navigate(id)
    }
}

fun Fragment.goto(id: Int, bundle: Bundle) {
    activity?.let {
        findNavController().navigate(id, bundle)
    }
}

fun Fragment.popBackStack() {
    activity?.let {
        findNavController().popBackStack()
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.softInputModeAdjustResize() {
    activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Fragment.softInputModeNothing() {
    activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}

fun Fragment.copyToClipboard(label: CharSequence, text: CharSequence, callback: (Unit) -> Unit) {
    activity?.let {
        (it.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?)?.apply {
            setPrimaryClip(ClipData.newPlainText(label, text))
            callback.invoke(Unit)
        }
    }
}

fun Fragment.toast(message: String) {
    activity?.toast(message)
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}