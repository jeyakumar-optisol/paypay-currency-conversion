package io.blix.photosapp.ui.sign_in

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.blix.photosapp.CoroutineScope
import io.blix.photosapp.LiveDataTestUtil
import io.blix.photosapp.MainCoroutineRule
import io.blix.photosapp.base.BaseTestClass
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.model.GoogleSignInModel
import io.digikraft.domain.model.signin.GoogleAuthenticationModel
import io.digikraft.domain.usecase.SaveUserLoginStateUseCase
import io.digikraft.domain.usecase.sign_in.SignInWithFacebookAuthenticationFirebaseUseCase
import io.digikraft.domain.usecase.sign_in.SignInWithGoogleAuthenticationFirebaseUseCase
import io.digikraft.result.SuccessApiResult
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config
import javax.inject.Inject


@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class SignInViewModelTest : BaseTestClass() {

    @Inject
    lateinit var preferenceStorage: IPreferenceStorage

    private lateinit var viewModel: SignInViewModel

    private fun createViewModel(): SignInViewModel {
        val signInUseCaseGoogle = SignInWithGoogleAuthenticationFirebaseUseCase(coroutineRule.testDispatcher, preferenceStorage)
        val signInUseCaseFacebook = SignInWithFacebookAuthenticationFirebaseUseCase(coroutineRule.testDispatcher, preferenceStorage)
        val saveUseStateUseCase = SaveUserLoginStateUseCase(coroutineRule.testDispatcher, preferenceStorage)
        return SignInViewModel(
            getApplicationContext(), coroutineRule.testDispatcher,
            signInUseCaseGoogle, signInUseCaseFacebook, saveUseStateUseCase
        )
    }

    @Test
    fun signInWithGoogle() {
        viewModel = createViewModel()
        viewModel.signInWithGoogle(
            GoogleAuthenticationModel(
                googleSignInModel = GoogleSignInModel(
                    email = "doniyor.rakhmanov@digikraft.io",
                    firstName = "Rakhmanov",
                    lastName = "Doniyor"
                ),
                null
            )
        )

//        assertEquals(1, 1)
    }

    @Test
    fun signInWithFacebook() {
    }

    @Test
    fun saveUserLoginState() {
        viewModel = createViewModel()
        coroutineRule.CoroutineScope().launch {
            val result = LiveDataTestUtil.getValue(viewModel.saveUserLoginState(true))
            assertTrue((result is SuccessApiResult))
        }
    }

}