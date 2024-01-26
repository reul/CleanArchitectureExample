package space.reul.cleanarchitectureexample.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import space.reul.cleanarchitectureexample.data.webapi.ShibaService
import kotlin.test.BeforeTest
import kotlin.test.Test

class ShibaRepositoryImplTest {
    private lateinit var subject: ShibaRepositoryImpl
    private lateinit var service: ShibaService

    @BeforeTest
    fun setUp() {
        service = mockk()

        coEvery { service.listUrls() } returns arrayListOf("url 1")

        subject = ShibaRepositoryImpl(service)
    }

    @Test
    fun `verify listShibas calls web service method`() = runTest {
        subject.listShibas()

        coVerify { service.listUrls() }
    }
}
