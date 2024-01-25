package space.reul.cleanarchitectureexample.domain.usecase

import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DoStuff(
    val backgroundContext: CoroutineContext,
) : UseCase<Int> {
    override suspend operator fun invoke(): Int = withContext(backgroundContext) {
        (0..10).forEach {
            println("$it")
        }

        0
    }
}
