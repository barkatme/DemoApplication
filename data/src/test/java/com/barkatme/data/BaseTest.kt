// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.data

import android.app.Application
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mockito

interface BaseTest : KoinTest {

    companion object {
        val application: Application = Mockito.mock(Application::class.java)
    }

    @Before
    fun before() {
        startDataKoin(application)
    }

    @After
    fun after() {
        stopKoin()
    }
}