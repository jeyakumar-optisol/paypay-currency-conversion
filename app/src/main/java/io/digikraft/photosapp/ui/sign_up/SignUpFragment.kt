package io.digikraft.photosapp.ui.sign_up

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentSignUpBinding
import io.digikraft.domain.model.signin.CreateFirebaseUserModel
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.fragment.popBackStack
import io.digikraft.utility.fragment.softInputModeAdjustResize
import io.digikraft.utility.fragment.softInputModeNothing
import io.digikraft.utility.validation.ValidationUtility.isSecurePassword
import io.digikraft.utility.validation.ValidationUtility.isValidEmail
import io.digikraft.utility.validation.ValidationUtility.isValidUsername

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
    FragmentSignUpBinding::inflate,
    SignUpViewModel::class.java
) {


    override fun onInit(savedInstanceState: Bundle?) {
        softInputModeNothing()

        initListener()
        initPreview()
    }

    private fun initPreview() {
        binding.usernameEditText.filters = arrayOf<InputFilter>(object : InputFilter.AllCaps() {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ) = source.toString().lowercase()
        })
    }

    private fun initListener() {
        binding.appBar.ivBack.setOnClickListener { popBackStack() }
        binding.signInButton.setOnClickListener { goto(R.id.goto_sign_in_fragment) }
        binding.signUpButton.setOnClickListener {
            val inputUsername = binding.usernameEditText.text.toString().trim()
            val inputEmail = binding.emailEditText.text.toString().trim()
            val inputPassword = binding.passwordEditText.text.toString().trim()
            val inputRepeatPassword = binding.repeatPasswordEditText.text.toString().trim()

            if (validateInputs(
                    inputUsername,
                    inputEmail,
                    inputPassword,
                    inputRepeatPassword,
                )
            ) {
                viewModel.createFirebaseUser(
                    CreateFirebaseUserModel(
                        inputUsername,
                        inputEmail,
                        inputPassword
                    )
                ).observe(this@SignUpFragment){
                    goto(R.id.menuHomeFragment)
                }
            }
        }
    }

    private fun validateInputs(
        inputUsername: String,
        inputEmail: String,
        inputPassword: String,
        inputRepeatPassword: String
    ): Boolean {
        if (!isValidUsername(inputUsername)) {
            toast("username should only contains fullstops")
            return false
        }
        if (!isValidEmail(inputEmail)) {
            toast("valid email address required")
            return false
        }
        if (!isSecurePassword(inputPassword)) {
            toast("required alphanumeric with eight more characters")
            return false
        }
        if (inputPassword != inputRepeatPassword) {
            toast("Password doesn't match")
            return false
        }
        return true
    }

    override fun onDestroyView() {
        softInputModeAdjustResize()
        super.onDestroyView()
    }

}