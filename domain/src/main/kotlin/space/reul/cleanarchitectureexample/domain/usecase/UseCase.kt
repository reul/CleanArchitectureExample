package space.reul.cleanarchitectureexample.domain.usecase

interface UseCase<T> {
    suspend operator fun invoke(): Result<T>
}
