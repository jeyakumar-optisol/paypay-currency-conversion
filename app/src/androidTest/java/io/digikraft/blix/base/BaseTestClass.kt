package io.digikraft.photosapp.base

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.digikraft.photosapp.MainCoroutineRule
import org.junit.Before
import org.junit.Rule

abstract class BaseTestClass{

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun before() {
        hiltAndroidRule.inject()
    }

}