package io.blix.photosapp.ui.reset_password

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentResetPasswordBinding
import io.digikraft.domain.model.ResetPasswordModel
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.fragment.hideKeyboard
import io.digikraft.utility.fragment.popBackStack
import io.digikraft.utility.validation.ValidationUtility.isValidEmail

@AndroidEntryPoint
class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding, ResetPasswordViewModel>(
    FragmentResetPasswordBinding::inflate, ResetPasswordViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.appBar.ivBack.setOnClickListener { popBackStack() }
        binding.signInButton.setOnClickListener { goto(R.id.goto_sign_in_fragment) }
        binding.resetPasswordMaterialButton.setOnClickListener {
            val inputEmail = binding.tiEmail.editText?.text.toString().trim()
            if (validateInputs(inputEmail)) {
                hideKeyboard()
                viewModel.sendResetPasswordEmail(ResetPasswordModel(inputEmail)).observe(this) {
                    toast("Password reset sent to the given email")
                }
            }
        }
    }

    private fun validateInputs(inputEmail: String): Boolean {
        if (!isValidEmail(inputEmail)) {
            toast("Invalid email")
            return false
        }
        return true
    }

}