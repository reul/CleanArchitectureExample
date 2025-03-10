package space.reul.cleanarchitectureexample.domain.usecase

import kotlinx.coroutines.withContext
import space.reul.cleanarchitectureexample.domain.model.Cats
import kotlin.coroutines.CoroutineContext

class ListCats(
    val backgroundContext: CoroutineContext,
    val repository: Repository,
) : UseCase<Cats> {
    override suspend operator fun invoke(): Result<Cats> =
        withContext(backgroundContext) {
            repository.listCats()
        }

    interface Repository {
        suspend fun listCats(): Result<Cats>
    }
}
