package space.reul.cleanarchitectureexample.domain.usecase

import kotlinx.coroutines.withContext
import space.reul.cleanarchitectureexample.domain.model.Urls
import kotlin.coroutines.CoroutineContext

class ListShibas(
    val backgroundContext: CoroutineContext,
    val repository: Repository
) : UseCase<Urls> {
    override suspend operator fun invoke(): Urls = withContext(backgroundContext) {
        repository.listShibas()
    }

    interface Repository {
        suspend fun listShibas(): Urls
    }
}
