package io.digikraft.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import org.jetbrains.anko.toast

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class AbstractFragment<VB : ViewBinding, VM : AbstractViewModel>(
    private val inflate: Inflate<VB>,
    private val viewModelClass: Class<VM>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    protected val abstractViewModel: VM by lazy { ViewModelProvider(this)[viewModelClass] }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)

        initObserver()
        abstractViewModel.onCreate()
        onInit(inflater, container, savedInstanceState)
        onInit(savedInstanceState)
        return binding.root
    }

    private fun initObserver() {
        //noop:
    }

    open fun onInit(savedInstanceState: Bundle?) {
        //noop
    }

    open fun onInit(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) {
        //noop
    }

    fun toast(string: String) {
        activity?.toast(string)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}