package io.paypay.base

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import io.paypay.utils.R


abstract class AbstractProgressDialog<B : ViewBinding>(
    context: Context,
    private val bindingFactory: (LayoutInflater) -> B,
) : BaseMaterialDialog<B>(context, bindingFactory) {

    override fun onCreate(binding: B) {
        setMessage("Loading..")
        circularProgressIndicator?.apply {
            setOnClickListener {
                setProgress(progress + 10)
            }
        }
        onInit(binding)
    }

    fun setIndeterminate(boolean: Boolean): AbstractProgressDialog<B> {
        circularProgressIndicator?.isIndeterminate = boolean
        return this
    }

    fun setMessage(string: String): AbstractProgressDialog<B> {
        binding.root.findViewById<TextView>(R.id.title)?.apply {
            text = string
        }
        return this
    }

    fun setProgress(int: Int): AbstractProgressDialog<B> {
        if (int == -1) {
            return this
        }
        if (circularProgressIndicator?.isIndeterminate == true) {
            circularProgressIndicator?.isIndeterminate = false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            circularProgressIndicator?.setProgress(int, true)
        } else {
            circularProgressIndicator?.progress = int
        }
        return this
    }

    fun build(): AbstractProgressDialog<B> {
        if (!isShowing()) {
            show()
        }
        return this
    }

    private val circularProgressIndicator: CircularProgressIndicator?
        get() = binding.root.findViewById(
            R.id.progressbar
        )

    abstract fun onInit(binding: B)
}