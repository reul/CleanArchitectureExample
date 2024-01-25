package space.reul.cleanarchitectureexample.domain.usecase

import kotlinx.coroutines.withContext
import space.reul.cleanarchitectureexample.domain.model.Urls
import kotlin.coroutines.CoroutineContext

class ListShibas(
    val backgroundContext: CoroutineContext,
    val repository: ShibasRepository
) : UseCase<Urls> {
    override suspend operator fun invoke(): Urls = withContext(backgroundContext) {
        repository.listShibas()
    }

    interface ShibasRepository {
        suspend fun listShibas(): Urls
    }
}
