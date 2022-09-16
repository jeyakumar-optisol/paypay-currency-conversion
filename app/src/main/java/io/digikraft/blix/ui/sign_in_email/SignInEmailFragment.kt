package io.blix.photosapp.ui.sign_in_email

import android.os.Bundle
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.BuildConfig
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentSignInEmailBinding
import io.digikraft.domain.model.signin.EmailAuthenticationModel
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.debug.Log
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.fragment.launchAndRepeatWithViewLifecycle
import io.digikraft.utility.fragment.popBackStack
import io.digikraft.utility.validation.ValidationUtility
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class SignInEmailFragment : BaseFragment<FragmentSignInEmailBinding, SignInEmailViewModel>
    (FragmentSignInEmailBinding::inflate, SignInEmailViewModel::class.java) {


    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initObserver()
        initListener()
    }

    private fun initObserver() {

    }

    private fun initListener() = with(binding) {
        appBar.ivBack.setOnClickListener { popBackStack() }
        resetPasswordButton.setOnClickListener { goto(R.id.resetPasswordFragment) }
        signUpButton.setOnClickListener { goto(R.id.signUpFragment) }
        signInButton.setOnClickListener {
            val inputEmail = emailTextInput.editText?.text.toString().trim()
            val inputPassword = passwordTextInput.editText?.text.toString().trim()

            if (validateInput(inputEmail, inputPassword)) {
                viewModel.authenticateFirebase(EmailAuthenticationModel(inputEmail, inputPassword))
                    .observe(viewLifecycleOwner) {
                        goto(R.id.menuHomeFragment)
                    }
            }
        }

        if (BuildConfig.DEBUG) {
            emailEditText.setText("test@gmail.com")
            passwordEditText.setText("test12345")
        }
    }

    private fun validateInput(
        inputEmail: String,
        inputPassword: String
    ): Boolean {
        if (!ValidationUtility.isValidEmail(inputEmail) && !ValidationUtility.isValidUsername(
                inputEmail
            )
        ) {
            toast("required valid email/username") //todo: string literals has to be in string.xml
            return false
        }

        if (inputPassword.length < 8) {
            toast("valid password required")
            return false
        }

        return true
    }

}