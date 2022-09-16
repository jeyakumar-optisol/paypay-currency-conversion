@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package io.blix.photosapp.ui.sign_in_email

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.blix.photosapp.CoroutineScope
import io.blix.photosapp.LiveDataTestUtil
import io.blix.photosapp.base.BaseTestClass
import io.blix.photosapp.observeForTesting
import io.blix.photosapp.ui.TestCoroutineRule
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.datasource.IUserSessionDataStoreRepository
import io.digikraft.domain.model.signin.EmailAuthenticationModel
import io.digikraft.domain.usecase.SaveUserLoginStateUseCase
import io.digikraft.domain.usecase.sign_in.SignInWithEmailFirebaseUseCase
import io.digikraft.result.SuccessApiResult
import io.digikraft.utility.debug.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class SignInEmailViewModelTest : BaseTestClass() {


    @Inject
    lateinit var preferenceStorage: IPreferenceStorage


    @Test
    fun authenticateFirebase()   {
        val viewModel = createViewModel()
        val job = coroutineRule.CoroutineScope().launch {
            val result = LiveDataTestUtil.getValue(viewModel.authenticateFirebase(EmailAuthenticationModel("test@gmail.com", "test12345")))
            if (result is SuccessApiResult) {
                assert(true)
            }
        }
        job.cancel()
    }

    private fun createViewModel(): SignInEmailViewModel {
        val signInWithEmailFirebaseUseCase = SignInWithEmailFirebaseUseCase(coroutineRule.testDispatcher, preferenceStorage)
        val saveUserLoginStateUseCase = SaveUserLoginStateUseCase(coroutineRule.testDispatcher, preferenceStorage)
        return SignInEmailViewModel(getApplicationContext(), coroutineRule.testDispatcher, signInWithEmailFirebaseUseCase, saveUserLoginStateUseCase)
    }
}