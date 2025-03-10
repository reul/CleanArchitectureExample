package space.reul.cleanarchitectureexample.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import space.reul.cleanarchitectureexample.data.webapi.CatService
import kotlin.test.BeforeTest
import kotlin.test.Test

class CatsRepositoryImplTest {
    private lateinit var subject: CatsRepositoryImpl
    private lateinit var service: CatService

    @BeforeTest
    fun setUp() {
        service = mockk()

        coEvery { service.listUrls() } returns
            Result.success(
                arrayListOf(
                    mockk {
                        every { url } returns "url 1"
                    },
                ),
            )

        subject = CatsRepositoryImpl(service)
    }

    @Test
    fun `verify listCats calls web service method`() =
        runTest {
            subject.listCats()

            coVerify { service.listUrls() }
        }
}
