package io.digikraft.photosapp.ui.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentSignInBinding
import io.digikraft.domain.model.signin.GoogleAuthenticationModel
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.debug.Log
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.nullablity.orElse
import io.digikraft.utility.playservices.FacebookAuthentication
import io.digikraft.utility.playservices.GoogleAuthentication
import io.digikraft.utility.playservices.await
import io.digikraft.utility.setup.Utility.printHashKey
import io.digikraft.utility.threading.runOnAsyncThread
import io.digikraft.utility.threading.runOnMainThread

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(
    FragmentSignInBinding::inflate,
    SignInViewModel::class.java
) {

    private var googleLoginLauncher: ActivityResultLauncher<Intent>? =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // ToDO:
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                runOnAsyncThread {
                    try {
                        val result: GoogleSignInAccount =
                            GoogleSignIn.getSignedInAccountFromIntent(result.data).await()
                        runOnMainThread {
                            googleAuthentication.setAuthResult(result)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

    private val googleAuthentication
            by lazy {
                GoogleAuthentication(
                    googleLoginLauncher,
                    this@SignInFragment,
                    resources.getString(R.string.default_web_client_id)
                )
            }

    private val facebookAuthentication by lazy {
        FacebookAuthentication(currentActivity).build()
    }

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initInstance()
        initListener()
    }

    private fun initInstance() {
        printHashKey(context) //todo: remove at production

        /*LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.d("facebookSignInButton", "action: user cancel")
                }

                override fun onError(error: FacebookException) {
                    error.localizedMessage?.let { it1 ->
                        Log.e("facebookSignInButton", it1)
                        toast(it1)
                    }
                }

                override fun onSuccess(result: LoginResult) {
                    //noop
                }
            })*/
    }

    private fun initListener() = with(binding) {
        signInEmailButton.setOnClickListener { goto(R.id.signInEmailFragment) }
        signUpButton.setOnClickListener { goto(R.id.signUpFragment) }
        googleSignInButton.setOnClickListener {
            googleAuthentication.signIn { signInModel ->
                signInModel.email?.let {
                    googleAuthentication.getAuthCredential()?.let { it1 ->
                        viewModel.signInWithGoogle(GoogleAuthenticationModel(signInModel, it1))
                            .observe(viewLifecycleOwner) {
                                viewModel.saveUserLoginState(true).observe(viewLifecycleOwner) {
                                    goto(R.id.menuHomeFragment)
                                }
                            }
                    }
                }.orElse {
                    toast(signInModel.error ?: "failed")
                }
            }
        }
        facebookSignInButton.setOnClickListener {
            facebookAuthentication.login {
                Log.e("JeyK", "login $it")
                viewModel.signInWithFacebook(it).observe(viewLifecycleOwner) {
                    viewModel.saveUserLoginState(true).observe(viewLifecycleOwner) {
                        goto(R.id.menuHomeFragment)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        facebookAuthentication.destroy()
    }
}