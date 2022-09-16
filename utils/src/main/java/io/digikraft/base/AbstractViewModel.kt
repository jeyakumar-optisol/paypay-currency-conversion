package io.digikraft.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import org.jetbrains.anko.toast


/*
* Injecting application instance to access context on the inheriting class
*
* Example:
* @HiltViewModel
* class SampleViewModel @Inject constructor(application: Application) : BaseViewModel(application)
* */
abstract class AbstractViewModel(application: Application) : AndroidViewModel(application) {
    protected val context: Context get() = getApplication<Application>()

    open fun toast(string: String) {
        context.toast(string)
    }

    open fun onProgressDialogCancelled() {
        //noop
    }

    abstract fun onCreate()
}