package io.digikraft.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.nativedevps.support.utility.language.Utility.getDeviceLocale
import io.digikraft.utility.activity.contentView
import io.digikraft.utility.language.ContextWrapper
import java.util.*


abstract class AbstractActivity<B : ViewDataBinding, VM : AbstractViewModel>(
    bindingFactory: Int,
    private val viewModelClass: Class<VM>,
) : AppCompatActivity() {
    protected val abstractViewModel: VM by lazy { ViewModelProvider(this).get(viewModelClass) }

    protected val binding: B by contentView(bindingFactory)

    private lateinit var _binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        preInit()
        initBinding()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObserver()
        abstractViewModel.onCreate()
        onInit(savedInstanceState)
    }

    open fun preInit() {
    }

    private fun initObserver() {
        //noop:
    }

    private fun initBinding() {
        //binding.lifecycleOwner = this
    }

    abstract fun onInit(savedInstanceState: Bundle?)


    override fun attachBaseContext(context: Context) {
        val lang = getLocale(context) ?: getDeviceLocale()?.language
        val contextWrapper = Locale(lang).let {
            Locale.setDefault(it)
            ContextWrapper.wrap(context, it)
        }
        super.attachBaseContext(contextWrapper)
    }

    open fun getLocale(context: Context): String? {
        return null
    }
}