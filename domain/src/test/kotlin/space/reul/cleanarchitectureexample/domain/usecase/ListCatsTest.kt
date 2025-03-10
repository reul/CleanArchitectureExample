package space.reul.cleanarchitectureexample.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ListCatsTest {
    private lateinit var subject: ListCats
    private lateinit var repository: ListCats.Repository

    @BeforeTest
    fun setUp() {
        repository = mockk()

        coEvery { repository.listCats() } returns Result.success(arrayListOf())

        subject =
            ListCats(
                Dispatchers.Unconfined,
                repository,
            )
    }

    @Test
    fun `verify repository_listCats method is called`() =
        runTest {
            subject.invoke()

            coVerify { repository.listCats() }
        }
}
