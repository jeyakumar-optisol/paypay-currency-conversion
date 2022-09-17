package io.digikraft.photosapp.ui.menu_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentMenuProfileBinding
import io.digikraft.ui.base.BaseFragment

@AndroidEntryPoint
class MenuProfileFragment : BaseFragment<FragmentMenuProfileBinding, ProfileViewModel>(
    FragmentMenuProfileBinding::inflate, ProfileViewModel::class.java
) {

}