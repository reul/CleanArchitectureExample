package space.reul.cleanarchitectureexample.data.repository

import space.reul.cleanarchitectureexample.data.webapi.CatService
import space.reul.cleanarchitectureexample.domain.model.Cats
import space.reul.cleanarchitectureexample.domain.usecase.ListCats

class CatsRepositoryImpl(
    private val webApi: CatService = CatService(),
) : ListCats.Repository {
    override suspend fun listCats(): Result<Cats> = webApi.listUrls()
}
