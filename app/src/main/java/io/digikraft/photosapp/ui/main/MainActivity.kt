package io.digikraft.photosapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.ActivityMainBinding
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.ui.base.BaseActivity
import io.digikraft.utility.debug.Log
import io.digikraft.utility.debug.Log.d
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    private var navController: NavController? = null

    @Inject
    lateinit var preferenceStorage: IPreferenceStorage

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initListener()
        initObserver()
        initData()
        initUi()
    }

    private fun initObserver() {
//        viewModel.userProfile.asLiveData().observe(this) {
//            //todo: update ui ${it.email}
//        }
    }

    private fun initData() {
        //todo: handle intent data
    }

    private fun initListener() {
        lifecycleScope.launch {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            val graph = navController?.navInflater?.inflate(R.navigation.main_navigation)
            graph?.let {
                if (viewModel.logged.first()) {
                    it.setStartDestination(R.id.menuHomeFragment)
                } else {
                    it.setStartDestination(R.id.introductionNewFragment)
                }
                navController?.setGraph(it, intent.extras)
            }
            binding.bottomNavigation.setupWithNavController(navController!!)
            navController?.addOnDestinationChangedListener { _, destination, _ ->
                run {
                    when (destination.id) {
                        R.id.menuHomeFragment, R.id.menuMarketFragment,
                        R.id.menuMyTicketsFragment, R.id.menuProfileFragment -> {
                            binding.bottomNavigation.visibility = View.VISIBLE
                        }
                        else -> {
                            binding.bottomNavigation.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun initUi() {
        //todo: handle ui update or drawings
    }
}